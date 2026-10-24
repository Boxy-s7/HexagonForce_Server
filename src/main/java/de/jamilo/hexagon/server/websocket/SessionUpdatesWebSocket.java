package de.jamilo.hexagon.server.websocket;

import de.jamilo.hexagon.server.application.user.UserSessionService;
import de.jamilo.hexagon.server.domain.websocket.Message;
import de.jamilo.hexagon.server.persistence.service.SessionService;
import io.quarkus.websockets.next.OnClose;
import io.quarkus.websockets.next.OnOpen;
import io.quarkus.websockets.next.WebSocket;
import io.quarkus.websockets.next.WebSocketConnection;
import jakarta.inject.Inject;

/** Push-only stream for the live session dashboard. */
@WebSocket(path = "/ws/dashboard/sessions")
public class SessionUpdatesWebSocket {

    @Inject
    SessionUpdateBroadcaster broadcaster;

    @Inject
    SessionService sessions;

    @OnOpen
    Message onOpen(WebSocketConnection connection) {
        broadcaster.register(connection);
        return broadcaster.snapshotMessage(sessions);
    }

    @OnClose
    void onClose(WebSocketConnection connection) {
        broadcaster.unregister(connection);
    }
}

