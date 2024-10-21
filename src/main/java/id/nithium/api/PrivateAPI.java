package id.nithium.api;

import cz.foresttech.forestredis.shared.IForestRedisPlugin;
import cz.foresttech.forestredis.shared.RedisManager;
import cz.foresttech.forestredis.shared.models.MessageTransferObject;
import cz.foresttech.forestredis.shared.models.RedisConfiguration;
import id.nithium.api.event.EventManager;
import id.nithium.api.redis.AsyncRedisReceivedMessageEvent;
import id.nithium.api.redis.RedisReceivedMessageEvent;
import id.nithium.libraries.proxima.Proxima;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@Getter
@Setter
public class PrivateAPI implements IForestRedisPlugin {

    @Getter
    private static PrivateAPI instance;
    private final Logger logger = Proxima.getLogger();
    private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private String[] channels = {};
    private RedisCredential redisCredential;
    private RedisManager redisManager;
    private EventManager eventManager;

    public PrivateAPI(RedisCredential redisCredential, String[] channels) {
        instance = this;

        this.redisCredential = redisCredential;
        this.channels = channels;

        RedisConfiguration redisConfiguration = new RedisConfiguration(
                redisCredential.getHost(),
                redisCredential.getPort(),
                redisCredential.getUsername(),
                redisCredential.getPassword(),
                redisCredential.isSsl()
        );

        redisManager = new RedisManager(this, "privapi", redisConfiguration);

        redisManager.setup(channels);
        redisManager.subscribe(channels);

        eventManager = new EventManager();
    }

    @Override
    public void runAsync(Runnable runnable) {
        executorService.submit(runnable);
    }

    @Override
    public void onMessageReceived(String s, MessageTransferObject messageTransferObject) {
        AsyncRedisReceivedMessageEvent asyncRedisReceivedMessageEvent = new AsyncRedisReceivedMessageEvent(s, messageTransferObject);
        executorService.submit(() -> eventManager.callEvent(asyncRedisReceivedMessageEvent));

        if (!asyncRedisReceivedMessageEvent.isCancelled()) {
            new Thread(() -> {
                eventManager.callEvent(new RedisReceivedMessageEvent(asyncRedisReceivedMessageEvent.getChannel(), asyncRedisReceivedMessageEvent.getMessageTransferObject()));
            });
        }
    }

    @Override
    public Logger logger() {
        return logger;
    }
}
