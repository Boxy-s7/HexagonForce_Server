package de.jamilo.hexagon.server.service;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.jamilo.hexagon.server.domain.websocket.Command;
import de.jamilo.hexagon.server.domain.websocket.Message;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton 
public class WorldService {

    @Inject 
    CommandService commandService;

    public void onMessage(Message message, Long userID) {
        switch (message.command()) {
            case "hexClick":
                onHexClick(message, userID);
                break;
            default:
                throw new IllegalArgumentException("Unknown command: " + message.command());
        }
        
    }

    private void onHexClick(Message message, Long userID) {
        int x = message.payload().get("x").asInt();
        int y = message.payload().get("y").asInt();

        boolean isValid = validateHexClick(x, y, userID);
        if (isValid) 
        {
            ObjectNode payload = JsonNodeFactory.instance.objectNode()
        .put("x", x)
        .put("y", y)
        .put("isValid", true);

            Command command = new Command(message.messageid(), "world" , "hexClick" , payload);
            commandService.sendCommand(command, userID);
        }
    }

    private boolean validateHexClick(int x, int y, Long userID) {
        return true;
    }



}
