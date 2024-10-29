package id.nithium.api;

import com.google.gson.Gson;
import id.nithium.api.exception.NithiumException;
import id.nithium.api.model.AbstractModel;
import id.nithium.api.type.DataType;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Getter
@Setter
public class PrivateAPI {

    @Getter
    private static PrivateAPI instance;

    private final String BASE_URL = "http://localhost:8080/v1/"; // Default -> https://api.nithium.id/api/v1/
    private final HttpClient httpClient;
    public final Gson GSON;
    private final CanopusPrivateAPI canopusPrivateAPI;

    public PrivateAPI(HttpClient httpClient) {
        this.httpClient = httpClient;
        GSON = new Gson();

        canopusPrivateAPI = new CanopusPrivateAPI(this);
    }

    public <T extends AbstractModel> T get(DataType dataType, String url, Class<T> clazz) throws IOException, InterruptedException {
        String url1 = BASE_URL + dataType.getName() + url;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url1))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {

            return GSON.fromJson(response.body(), clazz);
        } else {
            throw new NithiumException("Error: " + response.statusCode());
        }
    }

    public <T extends AbstractModel> T post(DataType dataType, String request, String url, Class<T> clazz) throws IOException, InterruptedException {
        String url1 = BASE_URL + dataType.getName() + url;

        HttpRequest request1 = HttpRequest.newBuilder()
                .uri(URI.create(url1))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(request))
                .build();

        HttpResponse<String> response = httpClient.send(request1, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {

            return GSON.fromJson(response.body(), clazz);
        } else {
            throw new NithiumException("Error: " + response.statusCode());
        }
    }
}
