package de.jamilo.hexagon.server.service;

import java.util.UUID;

import de.jamilo.hexagon.server.domain.command.HubCommand;
import de.jamilo.hexagon.server.persistence.service.SessionService;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton 
public class CommandService {

    @Inject 
    SessionService sessionService;

    public void sendCommand(HubCommand<?> command, UUID userId) {
        sessionService.sendToUser(userId, command);
    }
}
