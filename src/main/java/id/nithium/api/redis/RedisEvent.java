package id.nithium.api.redis;

import id.nithium.api.PrivateAPI;

public class RedisEvent {

    private final PrivateAPI privateAPI = PrivateAPI.getInstance();

    public RedisEvent() {
        privateAPI.getEventManager().registerListener(RedisReceivedMessageEvent.class, event -> {
        });
    }
}
