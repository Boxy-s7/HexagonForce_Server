package de.jamilo.hexagon.server.application.user;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.hibernate.boot.model.relational.InitCommand;

import de.jamilo.hexagon.server.domain.event.InitEvent;
import de.jamilo.hexagon.server.domain.session.SessionChanged;
import de.jamilo.hexagon.server.domain.session.SessionRecord;
import de.jamilo.hexagon.server.persistence.entity.SessionEntity;
import de.jamilo.hexagon.server.persistence.entity.UserEntity;
import io.quarkus.websockets.next.UserData.TypedKey;
import io.quarkus.websockets.next.WebSocketConnection;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserSessionService {
    private final ConcurrentMap<UUID, SessionEntity> pendingSessions = new ConcurrentHashMap<>();

    private final EntityManager entityManager;
    private final Event<SessionChanged> sessionChanges;

    private static final ConcurrentMap<String, Set<WebSocketConnection>> CONNECTIONS = new ConcurrentHashMap<>();
    private static final TypedKey<String> SESSION_ID = TypedKey.forString("sessionId");
    private static final TypedKey<String> USER_ID = TypedKey.forString("userId");
    private static final TypedKey<String> CONNECTION_STATE = TypedKey.forString("state");
    private static final String CONNECTION_STATE_INIT = "INIT";

    public UserSessionService(EntityManager entityManager, Event<SessionChanged> sessionChanges) {
        this.entityManager = entityManager;
        this.sessionChanges = sessionChanges;
    }

    public void setPendingSession(SessionEntity session) {
        pendingSessions.put(session.getId(), session);
        new Thread(() -> {
            try {
                Thread.sleep(600000); // Wait for 10 minutes
                pendingSessions.remove(session.getId());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    public void loginPendingSession(UUID sessionId) {
        SessionEntity session = pendingSessions.remove(sessionId);
        if (session != null) {
            session.setLoginAt(OffsetDateTime.now());
            entityManager.persist(session);
            entityManager.flush();
            sessionChanges.fire(new SessionChanged("login", toRecord(session)));
        }
    }

    @Transactional
    public boolean register(WebSocketConnection connection) {

        SessionEntity session = new SessionEntity(null, OffsetDateTime.now());
        entityManager.persist(session);
        entityManager.flush();

        sessionChanges.fire(new SessionChanged("login", toRecord(session)));
        UUID sessionId = session.getId();
        String userId = connection.pathParam("userId");
        connection.userData().put(SESSION_ID, sessionId.toString());
        connection.userData().put(USER_ID, userId);
        connection.userData().put(CONNECTION_STATE, CONNECTION_STATE_INIT);
        CONNECTIONS.computeIfAbsent(userId, ignored -> ConcurrentHashMap.newKeySet()).add(connection);^
        return true;
    }

    @Transactional 
    public boolean init(WebSocketConnection connection, InitEvent event) {
        return true;
    }
    @Transactional
    public void logout(WebSocketConnection connection) {
        String sessionId = connection.userData().get(SESSION_ID);
        String userId = connection.pathParam("userId");

        if (userId != null) {
            CONNECTIONS.computeIfPresent(userId, (ignored, userConnections) -> {
                userConnections.remove(connection);
                return userConnections.isEmpty() ? null : userConnections;
            });
        }
        if (sessionId != null) {
            SessionEntity session = entityManager.find(SessionEntity.class, sessionId);
            if (session != null && session.getLogoutAt() == null) {
                session.logout(OffsetDateTime.now());
                entityManager.flush();
                sessionChanges.fire(new SessionChanged("logout", toRecord(session)));
            }
        }
    }

    @Transactional
    public List<SessionRecord> findRecentSessions() {
        OffsetDateTime since = OffsetDateTime.now().minusHours(24);
        return entityManager.createQuery(
                "select s from SessionEntity s join fetch s.user "
                        + "where s.loginAt >= :since order by s.loginAt desc",
                SessionEntity.class)
                .setParameter("since", since)
                .getResultList()
                .stream()
                .map(this::toRecord)
                .toList();
    }

    private SessionRecord toRecord(SessionEntity session) {
        UserEntity user = session.getUser();
        return new SessionRecord(
                session.getId(),
                user.getId(),
                user.getUsername(),
                session.getLoginAt(),
                session.getLogoutAt());
    }

    @Transactional
    public UUID login(String sessionId, String username) {

        UserEntity user = entityManager.createQuery(
                "select u from UserEntity u where u.username = :username",
                UserEntity.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst()
                .orElseGet(() -> {
                    UserEntity newUser = new UserEntity(username);
                    entityManager.persist(newUser);
                    entityManager.flush();
                    return newUser;
                });

        SessionEntity session = pendingSessions.remove(UUID.fromString(sessionId));

        if (session == null) {
            return null;
        } else {
            session.setUser(user);
            session.setLoginAt(OffsetDateTime.now());
        }

        entityManager.persist(session);
        entityManager.flush();

        sessionChanges.fire(new SessionChanged("login", toRecord(session)));
        return session.getId();
    }

    @SuppressWarnings("null")
    public SessionRecord getSession(UUID sessionId) {
        SessionEntity session = entityManager.find(SessionEntity.class, sessionId);
        {
            return toRecord(session);
        }

    }
}
