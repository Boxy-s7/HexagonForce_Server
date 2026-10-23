package de.jamilo.hexagon.server.domain.event;

import de.jamilo.hexagon.server.domain.message.HubMessage;

public abstract class HubEvent<TPayload> extends HubMessage<TPayload>{
    public String event;

    public HubEvent() {
        
    }

    public HubEvent(String module, String event, TPayload payload, String id) {
        super(module, payload, id);
        this.event = event;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
    
}