package de.jamilo.hexagon.server.service;

import java.util.UUID;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import de.jamilo.hexagon.server.domain.command.WorldHexClickCommand;
import de.jamilo.hexagon.server.domain.event.HubEvent;
import de.jamilo.hexagon.server.domain.event.WorldHexClickEvent;
import de.jamilo.hexagon.server.domain.payload.WorldHexClickPayload;
import de.jamilo.hexagon.server.domain.websocket.Command;
import de.jamilo.hexagon.server.domain.websocket.Message;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton 
public class WorldService {

    @Inject 
    CommandService commandService;

    public void onEvent(HubEvent<?> event, UUID userID) {
        switch (event.event) {
            case "hexClick":
                onHexClick((WorldHexClickEvent) event, userID);
                break;
            default:
                throw new IllegalArgumentException("Unknown command: " + event.event);
        }
        
    }

    private void onHexClick(WorldHexClickEvent event, UUID userID) {
        int x = event.payload.x;
        int y = event.payload.y;

        boolean isValid = validateHexClick(x, y, userID);
        if (isValid) 
        {
            event.payload.isValid = true;

            WorldHexClickCommand command = new WorldHexClickCommand();
            command.payload = event.payload;
            commandService.sendCommand(command, userID);
        }
    }

    private boolean validateHexClick(int x, int y, UUID userID) {
        return true;
    }



}
