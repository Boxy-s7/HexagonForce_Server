package de.jamilo.hexagon.server.websocket;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import de.jamilo.hexagon.server.application.user.UserSessionService;
import de.jamilo.hexagon.server.domain.event.HubEvent;
import de.jamilo.hexagon.server.domain.event.HubEventList;
import de.jamilo.hexagon.server.domain.session.SessionRecord;
import de.jamilo.hexagon.server.domain.session.UserSession;
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
@WebSocket(path = "/ws/{sessionToken}")
public class WebSocketEndpoint {

    private static final TypedKey<String> CONNECTION_STATE = TypedKey.forString("state");
    private static final String CONNECTION_STATE_INIT = "INIT";

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
    void onMessage(HubEvent<?> message, WebSocketConnection connection) {
        String state = connection.userData().get(CONNECTION_STATE);
        if (state.equals(CONNECTION_STATE_INIT)) {
            if (message.module.equals(HubEventList.INIT)) {
                userSessionService.login(connection)
            } else {
                System.out.println("TODO LOGOUT");
            }
        } else  
        UserSession session = userSessionService.getUserSession(connection);
        if (session != null) {
            messageService.onEvent(message, session);
        }
    }
}
