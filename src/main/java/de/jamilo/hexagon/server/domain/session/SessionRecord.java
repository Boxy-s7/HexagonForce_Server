package de.jamilo.hexagon.server.domain.session;

import java.time.OffsetDateTime;
import java.util.UUID;

/** Data exposed to the session dashboard. */
public record SessionRecord(
        UUID id,
        UUID userId,
        UUID deviceId,
        UUID sessionId,
        String username,
        OffsetDateTime loginAt,
        OffsetDateTime logoutAt,
        int lastEvent,
        int lastCommand) {
}
