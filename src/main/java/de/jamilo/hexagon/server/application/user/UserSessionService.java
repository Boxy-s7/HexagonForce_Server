package de.jamilo.hexagon.server.application.user;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.hibernate.boot.model.relational.InitCommand;

import de.jamilo.hexagon.server.domain.event.InitEvent;
import de.jamilo.hexagon.server.domain.session.SessionChanged;
import de.jamilo.hexagon.server.domain.session.SessionRecord;
import de.jamilo.hexagon.server.domain.session.UserSession;
import de.jamilo.hexagon.server.persistence.entity.SessionEntity;
import de.jamilo.hexagon.server.persistence.entity.UserEntity;
import de.jamilo.hexagon.server.persistence.service.SessionService;
import de.jamilo.hexagon.server.persistence.service.UserService;
import io.quarkus.websockets.next.UserData.TypedKey;
import io.quarkus.websockets.next.WebSocketConnection;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import net.bytebuddy.utility.dispatcher.JavaDispatcher.Instance;

@ApplicationScoped
public class UserSessionService {

    @Inject 
    SessionService sessionService;

    @Inject 
    UserService userService;


    private final ConcurrentMap<UUID, SessionEntity> pendingSessions = new ConcurrentHashMap<>();

    private final Event<SessionChanged> sessionChanges;

    private static final TypedKey<String> SESSION_ID = TypedKey.forString("sessionId");
    private static final TypedKey<String> USER_ID = TypedKey.forString("userId");
    private static final TypedKey<String> CONNECTION_STATE = TypedKey.forString("state");
    private static final String CONNECTION_STATE_INIT = "INIT";

    private final Map<String, UserSession> userSessionMap = new ConcurrentHashMap<>();

    public UserSessionService(Event<SessionChanged> sessionChanges) {
        this.sessionChanges = sessionChanges;
    }

    @Transactional
    public boolean register(WebSocketConnection connection) {

        UUID userId = UUID.fromString(connection.pathParam("userId"));
        UserEntity user = userService.findById(userId);

        if (user == null) {
            System.out.println("Irgendwas falsch...");
            return false;
        }
        
        connection.userData().put(USER_ID, userId.toString());
        connection.userData().put(CONNECTION_STATE, CONNECTION_STATE_INIT);

        UserSession session = this.userSessionMap.computeIfAbsent(CONNECTION_STATE_INIT, (String key) -> {
          return createUserSession(connection);  
        });
        


        sessionChanges.fire(new SessionChanged("register", toRecord(session.getSession())));
        return true;
    }

    private UserSession createUserSession(WebSocketConnection connection) {
        

        SessionEntity session = new SessionEntity(null, OffsetDateTime.now());
        session.setLastCommand(0);
        session.setConnections(0);
        session.setLastMessage(0);
        session.setUser(null);
        session = sessionService.create(session);
        UUID sessionId = session.getId();
        connection.userData().put(SESSION_ID, sessionId.toString());
        return new UserSession(null, CONNECTION_STATE_INIT, session)
    }

    @Transactional 
    public boolean init(WebSocketConnection connection, InitEvent event) {
        return true;
    }

    private void saveIfChanged(SessionEntity entity) {
        if (entity != null) {
            
        }
    }


    @Transactional
    public UUID login(WebSocketConnection connection) {

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

    @Transactional
    public void logout(WebSocketConnection connection) {
        String userId = connection.userData().get(USER_ID);

        if (userId != null) {
            UserSession session = userSessionMap.get(userId);
            if (session != null) {
                SessionEntity entity = session.removeConnection(connection);
                saveIfChanged(entity);
            } 


            CONNECTIONS.computeIfPresent(userId, (ignored, userConnections) -> {
                userConnections.remove(connection);
                return userConnections.isEmpty() ? null : userConnections;
            });
        }
        if (sessionId != null) {
            SessionEntity session = entityManager.find(SessionEntity.class, sessionId);
            if (session != null && session.getLogoutAt() == null) {
                session.setLogoutAt(OffsetDateTime.now());
                entityManager.flush();
                sessionChanges.fire(new SessionChanged("logout", toRecord(session)));
            }
        }
    }
    @SuppressWarnings("null")
    public SessionRecord getSession(UUID sessionId) {
        SessionEntity session = entityManager.find(SessionEntity.class, sessionId);
        {
            return toRecord(session);
        }

    }

    public UserSession getUserSession(WebSocketConnection connection) {
        String userId = connection.userData().get(USER_ID);
        return this.userSessionMap.get(userId);
    }

    public UserSession getOrCreateUserSessionForDeviceToken(UUID deviceToken) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserSessionForDeviceToken'");
    }

    public UUID createUserGetDeviceToken() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUserGetDeviceToken'");
    }
}
