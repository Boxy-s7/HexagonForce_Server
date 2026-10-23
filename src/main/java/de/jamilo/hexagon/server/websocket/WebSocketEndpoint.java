package de.jamilo.hexagon.server.websocket;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import de.jamilo.hexagon.server.application.user.UserSessionService;
import de.jamilo.hexagon.server.domain.session.SessionRecord;
import de.jamilo.hexagon.server.domain.websocket.Command;
import de.jamilo.hexagon.server.domain.websocket.Message;
import de.jamilo.hexagon.server.service.MessageService;
import io.quarkus.websockets.next.OnClose;
import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.OnTextMessage;
import io.quarkus.websockets.next.UserData.TypedKey;
import io.quarkus.websockets.next.WebSocket;
import io.quarkus.websockets.next.WebSocketConnection;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;

/** User WebSocket endpoint. Connect using /ws/{userId}. */
@WebSocket(path = "/ws/{userId}")
public class WebSocketEndpoint {


    @Inject
    UserSessionService userSessionService;

    @Inject
    MessageService messageService;


    @OnOpen
    void onOpen(WebSocketConnection connection) {
        String userId = connection.pathParam("userId");
        if (userSessionService.register(connection)) {
            System.out.println("Session registered for: " + userId);
        } else {
            System.out.println("Session not registered for: " + userId);
        }
    }

    @OnClose
    void onClose(WebSocketConnection connection) {
        userSessionService.logout(connection);
    }

    @OnTextMessage
    void onMessage(Message message, WebSocketConnection connection) {
        SessionRecord session = userSessionService.getSession(UUID.fromString(connection.userData().get(SESSION_ID)));
        if (session != null) {
            messageService.onMessage(message, session.userId());
        }
    }

    public static Uni<Void> sendToUser(long userId, Command message) {
        return sendToConnections(
                CONNECTIONS.get(String.valueOf(userId)),
                message);
    }

    public static Uni<Void> sendToUsers(Collection<Long> userIds, Command message) {
        List<WebSocketConnection> connections = userIds.stream()
                .filter(Objects::nonNull)
                .distinct()
                .map(userId -> CONNECTIONS.get(String.valueOf(userId)))
                .filter(Objects::nonNull)
                .flatMap(Set::stream)
                .toList();

        return sendToConnections(connections, message);
    }

    public static Uni<Void> sendToAll(Command message) {
        List<WebSocketConnection> connections = CONNECTIONS.values().stream()
                .flatMap(Set::stream)
                .toList();

        return sendToConnections(connections, message);
    }

    private static Uni<Void> sendToConnections(
            Collection<WebSocketConnection> connections,
            Command message) {

        if (connections == null || connections.isEmpty()) {
            return Uni.createFrom().voidItem();
        }

        List<Uni<Void>> sends = connections.stream()
                .map(connection -> connection.sendText(message))
                .toList();

        return Uni.combine().all().unis(sends).discardItems();
    }

    private String findCookie(List<String> headers, String name) {
        if (headers == null) {
            return null;
        }

        for (String header : headers) {
            for (String part : header.split(";")) {
                String[] pair = part.trim().split("=", 2);
                if (pair.length == 2 && pair[0].equals(name)) {
                    return pair[1];
                }
            }
        }
        return null;
    }
}
