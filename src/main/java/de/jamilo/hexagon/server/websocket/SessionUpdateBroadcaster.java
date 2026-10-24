package de.jamilo.hexagon.server.websocket;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.jamilo.hexagon.server.application.user.UserSessionService;
import de.jamilo.hexagon.server.domain.session.SessionChanged;
import de.jamilo.hexagon.server.domain.session.SessionRecord;
import de.jamilo.hexagon.server.domain.websocket.Message;
import de.jamilo.hexagon.server.persistence.entity.SessionEntity;
import de.jamilo.hexagon.server.persistence.entity.UserEntity;
import de.jamilo.hexagon.server.persistence.service.SessionService;
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

    public Message snapshotMessage(SessionService sessions) {
        return new Message(
                UUID.randomUUID().toString(),
                "session",
                "snapshot",
                objectMapper.valueToTree(toRecords(sessions.findRecentSessions())));
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

        private List<SessionRecord> toRecords(List<SessionEntity> entities) {
            return entities.stream().map((SessionEntity entity) -> {
                return toRecord(entity);
            }).toList();
        }
        private SessionRecord toRecord(SessionEntity session) {
        UserEntity user = session.getUser();
        return new SessionRecord(
                session.getId(),
                user.getId(),
                session.getDeviceToken(),
                session.getSessionToken(),
                user.getUsername(),
                session.getLoginAt(),
                session.getLogoutAt(),
            session.getLastMessage(),
        session.getLastCommand());
    }
}
