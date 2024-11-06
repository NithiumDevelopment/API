package id.nithium.api;

import com.google.gson.Gson;
import id.nithium.api.exception.NithiumException;
import id.nithium.api.type.DataType;
import lombok.Getter;
import lombok.Setter;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPut;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import java.io.IOException;

@Getter
@Setter
public class NithiumAPI {

    @Getter
    private static NithiumAPI instance;                                         

    private String BASE_URL = "http://localhost:8080/v1/";
    private final CloseableHttpClient httpClient;
    public final Gson GSON;

    public NithiumAPI(CloseableHttpClient httpClient) {
        instance = this;

        this.httpClient = httpClient;
        GSON = new Gson();
    }

    public <T> T GET(DataType dataType, String url, Class<T> clazz) throws NithiumException {
        String httpUrl = BASE_URL + dataType.getUrl() + url;

        try {
            CloseableHttpResponse response = httpClient.execute(new HttpGet(httpUrl));

            String json = EntityUtils.toString(response.getEntity());

            return GSON.fromJson(json, clazz);
        } catch (IOException | ParseException e) {
            throw new NithiumException(e.getLocalizedMessage());
        }
    }

    public void PUT(DataType dataType, String url, Object object) throws NithiumException {
        String httpUrl = BASE_URL + dataType.getUrl() + url;

        HttpPut httpPut = new HttpPut(httpUrl);
        String json = GSON.toJson(object);
        StringEntity stringEntity = new StringEntity(json);
        httpPut.addHeader("Content-Type", "application/json");
        httpPut.setEntity(stringEntity);

        try {
            CloseableHttpResponse response = httpClient.execute(httpPut);
        } catch (IOException e) {
            throw new NithiumException(e.getLocalizedMessage());
        }
    }
}
