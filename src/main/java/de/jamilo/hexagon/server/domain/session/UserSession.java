package de.jamilo.hexagon.server.domain.session;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

import de.jamilo.hexagon.server.domain.command.HubCommand;
import de.jamilo.hexagon.server.persistence.entity.SessionEntity;
import de.jamilo.hexagon.server.persistence.entity.UserEntity;
import io.quarkus.websockets.next.UserData.TypedKey;
import io.quarkus.websockets.next.WebSocketConnection;

public class UserSession {

    private UserEntity user;

    private SessionEntity entity;
    private final LinkedList<WebSocketConnection> connections = new LinkedList<>();
    private final LinkedList<HubCommand> lastCommands = new LinkedList<>();
    private int lastCommand;

    private static final TypedKey<Integer> lastCommandKey = TypedKey.forInt("last_command");

    public UserSession() {
        lastCommand = 0;
    }

    public UserSession(UserEntity user, String sessionToken, SessionEntity entity) {
        this();
        this.user = user;
        this.entity = entity;
    }

    public SessionEntity sendCommand(HubCommand<?> command, UUID userId) {

        if (userId == null || userId.equals(user.getId())) {
            storeCommand(command);
            sendToConnections(command);
            return entity;
        }
        return null;
    }

    public SessionEntity addConnection(WebSocketConnection connection) {
        connections.add(connection);
        entity.addConnection();
        return entity;
    }

    public SessionEntity removeConnection(WebSocketConnection connection) {
        if (connections.remove(connection)) {     
            entity.removeConnection();
            return entity;
        }
        return null;
    }

    private void storeCommand(HubCommand<?> command) {
        command.count = ++lastCommand;
        lastCommands.add(command);
        if (lastCommands.size() > 10) {
            lastCommands.removeFirst();
        }
    }

    private void sendToConnections(HubCommand<?> command) {
        Iterator<WebSocketConnection> iterator = connections.iterator();
        while (iterator.hasNext()) {
            WebSocketConnection connection = iterator.next();

            if (!connection.isOpen()) {
                iterator.remove();
                continue;
            }
            if (lastCommand - connection.userData().get(lastCommandKey) != 1) {
                handleLaggyConnection(connection);
                continue;
            }
            connection.sendText(command)
                    .subscribe().with(
                            success -> {
                                connection.userData().put(lastCommandKey, lastCommand);
                            },
                            failure -> iterator.remove() 
                    );
        }
    }

    private void handleLaggyConnection(WebSocketConnection connection) {
        System.out.println("Laggy connection " + connection.toString());
    }

    public UUID getUserId() {
        return this.user.getId();
    }

    public SessionEntity getSession() {
        return entity;
    }
    public UUID getSessionId() {
        return this.entity.getId();
    }

    public String getUserDisplay() {
        return user.getUsername();
    }
}
