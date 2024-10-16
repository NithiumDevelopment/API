package id.nithium.api.redis;

import cz.foresttech.forestredis.shared.events.IRedisMessageReceivedEvent;
import cz.foresttech.forestredis.shared.models.MessageTransferObject;
import id.nithium.api.PrivateAPI;
import id.nithium.api.event.Event;
import lombok.Getter;

public class AsyncRedisReceivedMessageEvent extends Event implements IRedisMessageReceivedEvent {

    private final PrivateAPI privateAPI = PrivateAPI.getInstance();
    private final String channel;
    @Getter
    private final MessageTransferObject messageTransferObject;

    public AsyncRedisReceivedMessageEvent(String channel, MessageTransferObject messageTransferObject) {
        this.channel = channel;
        this.messageTransferObject = messageTransferObject;
    }

    @Override
    public String getSenderIdentifier() {
        return messageTransferObject.getSenderIdentifier();
    }

    @Override
    public String getChannel() {
        return channel;
    }

    @Override
    public String getMessage() {
        return messageTransferObject.getMessage();
    }

    @Override
    public long getTimeStamp() {
        return messageTransferObject.getTimestamp();
    }

    @Override
    public <T> T getMessageObject(Class<T> aClass) {
        return messageTransferObject.parseMessageObject(aClass);
    }

    @Override
    public boolean isSelfSender() {
        return messageTransferObject.getSenderIdentifier().equals(privateAPI.getRedisManager().getServerIdentifier());
    }
}
