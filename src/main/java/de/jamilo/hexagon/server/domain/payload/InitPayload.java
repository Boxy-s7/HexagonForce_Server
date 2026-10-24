package de.jamilo.hexagon.server.domain.payload;

import java.util.UUID;

public class InitPayload {
    public UUID deviceToken;
    public UUID sessionToken;
    public UUID userId;
    public String userDisplay; 
}
