package de.jamilo.hexagon.server.domain.command;

import de.jamilo.hexagon.server.domain.message.HubMessage;

public abstract class HubCommand<TPayload> extends HubMessage<TPayload> {
    public String command;

    public HubCommand() {
        super();
    }

    public HubCommand(String module, String command, TPayload payload, String id) {
        super(module, payload, id);
        this.command = command;
    }


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
    

}
