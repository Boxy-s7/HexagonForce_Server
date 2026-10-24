package de.jamilo.hexagon.server.domain.event;

import de.jamilo.hexagon.server.domain.command.HubCommandList;
import de.jamilo.hexagon.server.domain.payload.WorldHexClickPayload;

public class WorldHexClickEvent extends HubEvent<WorldHexClickPayload>{

    public final String event = HubCommandList.WORLD_HEX_CLICK;
}
