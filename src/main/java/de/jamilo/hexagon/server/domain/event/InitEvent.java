package de.jamilo.hexagon.server.domain.event;

import de.jamilo.hexagon.server.domain.command.HubCommandList;
import de.jamilo.hexagon.server.domain.payload.InitPayload;

public class InitEvent extends HubEvent<InitPayload> {
public final String event = HubCommandList.INIT;
}
