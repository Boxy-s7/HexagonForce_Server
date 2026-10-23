package de.jamilo.hexagon.server.service;

import de.jamilo.hexagon.server.domain.websocket.Command;
import de.jamilo.hexagon.server.websocket.WebSocketEndpoint;
import jakarta.inject.Singleton;

@Singleton 
public class CommandService {

    public void sendCommand(Command command, long userId) {
        WebSocketEndpoint.sendToUser(userId, command);
    }
}
