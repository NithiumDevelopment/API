package id.nithium.api;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.Setter;
import java.net.http.HttpClient;

@Getter
@Setter
public class NithiumAPI {

    @Getter
    private static NithiumAPI instance;

    private String BASE_URL = "http://localhost:8080/v1/";
    private final HttpClient httpClient;
    public final Gson GSON;

    public NithiumAPI(HttpClient httpClient) {
        this.httpClient = httpClient;
        GSON = new Gson();
    }

}
