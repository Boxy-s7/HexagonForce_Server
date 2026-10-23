package de.jamilo.hexagon.server.websocket;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.jamilo.hexagon.server.application.user.UserSessionService;
import de.jamilo.hexagon.server.domain.session.SessionChanged;
import de.jamilo.hexagon.server.domain.websocket.Message;
import io.quarkus.websockets.next.WebSocketConnection;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.event.TransactionPhase;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class SessionUpdateBroadcaster {

    private static final Logger LOG = Logger.getLogger(SessionUpdateBroadcaster.class);
    private final Set<WebSocketConnection> connections = ConcurrentHashMap.newKeySet();

    @Inject
    ObjectMapper objectMapper;

    public void register(WebSocketConnection connection) {
        connections.add(connection);
    }

    public void unregister(WebSocketConnection connection) {
        connections.remove(connection);
    }

    public Message snapshotMessage(UserSessionService sessions) {
        return new Message(
                UUID.randomUUID().toString(),
                "session",
                "snapshot",
                objectMapper.valueToTree(sessions.findRecentSessions()));
    }

    void onSessionChanged(@Observes(during = TransactionPhase.AFTER_SUCCESS) SessionChanged update) {
        Message message = new Message(
                UUID.randomUUID().toString(),
                "session",
                update.command(),
                objectMapper.valueToTree(update.session()));

        for (WebSocketConnection connection : connections) {
            Uni<Void> send = connection.sendText(message);
            send.subscribe().with(
                    ignored -> { },
                    failure -> {
                        LOG.warn("Could not send session update; removing closed connection", failure);
                        connections.remove(connection);
                    });
        }
    }
}
