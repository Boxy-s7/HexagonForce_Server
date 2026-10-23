package de.jamilo.hexagon.server.application.user;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.logging.Logger;

@ApplicationScoped
public class UserService {
    private static final Logger LOG = Logger.getLogger(UserService.class.getName());

    public String createPlayer() {
        // TODO: Spieler erzeugen und echtes user-token zurückgeben.
        LOG.info("/* create player */");
        return "stub-token";
    }

    public String resolveUser(String token) {
        // TODO: Token prüfen und User auflösen.
        LOG.info("/* resolve user */");
        return "user_1";
    }
}
