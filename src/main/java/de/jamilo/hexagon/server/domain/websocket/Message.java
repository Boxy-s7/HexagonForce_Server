package de.jamilo.hexagon.server.domain.websocket;

import com.fasterxml.jackson.databind.JsonNode;

    public record Message(String messageid, String modul, String command, JsonNode payload) {
    }
