package id.nithium.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RedisCredential {
    private String host, username, password;
    private int port;
    private boolean ssl;
}
