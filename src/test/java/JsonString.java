import com.google.gson.Gson;
import id.nithium.api.model.Server;
import id.nithium.libraries.proxima.Proxima;

import java.util.UUID;

public class JsonString {

    public static void main(String[] args) {
        Server server = Server.builder()
                .name("s001")
                .port(25565)
                .environment(null)
                .containerId(UUID.randomUUID().toString())
                .serverType(null)
                .modeType(null)
                .restricted(true)
                .maximumSize(10)
                .build();

        System.out.println(server.toString());
    }
}
