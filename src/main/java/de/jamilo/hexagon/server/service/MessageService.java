package de.jamilo.hexagon.server.service;

import de.jamilo.hexagon.server.domain.websocket.Message;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton 
public class MessageService {

    @Inject
    public WorldService worldService;

    public MessageService() {
    }

    public void onMessage(Message message, Long userID) {
        switch (message.modul()) {
            case "world":
                worldService.onMessage(message, userID);
                break;
            default:
                throw new IllegalArgumentException("Unknown modul: " + message.modul());
        }
    }
}
