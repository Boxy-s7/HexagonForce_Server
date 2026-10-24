package de.jamilo.hexagon.server.domain.command;

import de.jamilo.hexagon.server.domain.payload.WorldHexClickPayload;

public class WorldHexClickCommand extends HubCommand<WorldHexClickPayload>{
    public final String command = HubCommandList.WORLD_HEX_CLICK;
}
