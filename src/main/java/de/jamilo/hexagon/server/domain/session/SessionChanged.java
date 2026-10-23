package de.jamilo.hexagon.server.domain.session;

/** Published after a login or logout transaction commits. */
public record SessionChanged(String command, SessionRecord session) {
}
