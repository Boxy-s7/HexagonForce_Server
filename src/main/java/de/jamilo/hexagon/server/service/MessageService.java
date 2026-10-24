package de.jamilo.hexagon.server.service;

import java.util.UUID;

import de.jamilo.hexagon.server.domain.event.HubEvent;
import de.jamilo.hexagon.server.domain.session.UserSession;
import de.jamilo.hexagon.server.domain.websocket.Message;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton 
public class MessageService {

    @Inject
    public WorldService worldService;

    public MessageService() {
    }

    public void onEvent(HubEvent<?> event, UserSession us) {
        us.getSession().
        switch (event.module) {
            case "world":
                worldService.onEvent(event, us.getUserId());
                break;
            default:
                throw new IllegalArgumentException("Unknown modul: " + event.module);
        }
    }
}
