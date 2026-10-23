package de.jamilo.hexagon.server.domain.message;

public abstract class HubMessage<TPayload> {

    public String id;
    public String module;
    public TPayload payload;
    
    @SuppressWarnings("null")
    public HubMessage() {
        this.payload = null;
    }
    
    public HubMessage(String module, TPayload payload, String id) {
        this.id = id;
        this.module = module;
        this.payload = payload;
    }

    public String getModule() {
        return module;
    }
    public void setModule(String module) {
        this.module = module;
    }
    public TPayload getPayload() {
        return payload;
    }
    public void setPayload(TPayload payload) {
        this.payload = payload;
    }
}
