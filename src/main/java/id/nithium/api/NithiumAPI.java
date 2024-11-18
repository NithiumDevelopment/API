package id.nithium.api;

import com.google.gson.Gson;
import id.nithium.api.exception.NithiumException;
import id.nithium.api.type.DataType;
import lombok.Getter;
import lombok.Setter;
import org.apache.hc.client5.http.classic.methods.HttpDelete;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
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

    public <T> NithiumHttpResponse<T> GET(DataType dataType, String url, String apiKey, Class<T> clazz) throws NithiumException {
        String httpUrl = BASE_URL + dataType.getUrl() + url;

        try {
            HttpGet httpGet = new HttpGet(httpUrl);
            httpGet.addHeader("X_API_KEY", apiKey);

            CloseableHttpResponse response = httpClient.execute(httpGet);

            String json = EntityUtils.toString(response.getEntity());

            NithiumHttpResponse<T> nithiumHttpResponse = new NithiumHttpResponse<>(response, GSON.fromJson(json, clazz));
            if (nithiumHttpResponse.response().getCode() != 200) throw new NithiumException(nithiumHttpResponse.response().getReasonPhrase());

            return nithiumHttpResponse;
        } catch (IOException | ParseException e) {
            throw new NithiumException(e.getLocalizedMessage());
        }
    }

    public <T> NithiumHttpResponse<T> GET(DataType dataType, String url, Class<T> clazz) throws NithiumException {
        String httpUrl = BASE_URL + dataType.getUrl() + url;

        try {
            CloseableHttpResponse response = httpClient.execute(new HttpGet(httpUrl));

            String json = EntityUtils.toString(response.getEntity());

            NithiumHttpResponse<T> nithiumHttpResponse = new NithiumHttpResponse<>(response, GSON.fromJson(json, clazz));
            if (nithiumHttpResponse.response().getCode() != 200) throw new NithiumException(nithiumHttpResponse.response().getReasonPhrase());

            return nithiumHttpResponse;
        } catch (IOException | ParseException e) {
            throw new NithiumException(e.getLocalizedMessage());
        }
    }

    public NithiumHttpResponse<Object> POST(DataType dataType, String url, String apiKey, Object object) {
        String httpUrl = BASE_URL + dataType.getUrl() + url;

        HttpPost httpPost = new HttpPost(httpUrl);
        String json = GSON.toJson(object);
        StringEntity stringEntity = new StringEntity(json);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.addHeader("X_API_KEY", apiKey);
        httpPost.setEntity(stringEntity);

        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);

            NithiumHttpResponse<Object> nithiumHttpResponse = new NithiumHttpResponse<>(response, object);
            if (nithiumHttpResponse.response().getCode() != 200) throw new NithiumException(nithiumHttpResponse.response().getReasonPhrase());

            return nithiumHttpResponse;
        } catch (IOException e) {
            throw new NithiumException(e.getLocalizedMessage());
        }
    }

    public NithiumHttpResponse<Object> POST(DataType dataType, String url, Object object) {
        String httpUrl = BASE_URL + dataType.getUrl() + url;

        HttpPost httpPost = new HttpPost(httpUrl);
        String json = GSON.toJson(object);
        StringEntity stringEntity = new StringEntity(json);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setEntity(stringEntity);

        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return new NithiumHttpResponse<>(response, object);
        } catch (IOException e) {
            throw new NithiumException(e.getLocalizedMessage());
        }
    }

    public NithiumHttpResponse<Object> PUT(DataType dataType, String url, String apiKey, Object object) throws NithiumException {
        String httpUrl = BASE_URL + dataType.getUrl() + url;

        HttpPut httpPut = new HttpPut(httpUrl);
        String json = GSON.toJson(object);
        StringEntity stringEntity = new StringEntity(json);
        httpPut.addHeader("Content-Type", "application/json");
        httpPut.addHeader("X_API_KEY", apiKey);
        httpPut.setEntity(stringEntity);

        try {
            CloseableHttpResponse response = httpClient.execute(httpPut);

            NithiumHttpResponse<Object> nithiumHttpResponse = new NithiumHttpResponse<>(response, object);
            if (nithiumHttpResponse.response().getCode() != 200) throw new NithiumException(nithiumHttpResponse.response().getReasonPhrase());

            return nithiumHttpResponse;
        } catch (IOException e) {
            throw new NithiumException(e.getLocalizedMessage());
        }
    }

    public NithiumHttpResponse<Object> PUT(DataType dataType, String url, Object object) throws NithiumException {
        String httpUrl = BASE_URL + dataType.getUrl() + url;

        HttpPut httpPut = new HttpPut(httpUrl);
        String json = GSON.toJson(object);
        StringEntity stringEntity = new StringEntity(json);
        httpPut.addHeader("Content-Type", "application/json");
        httpPut.setEntity(stringEntity);

        try {
            CloseableHttpResponse response = httpClient.execute(httpPut);

            NithiumHttpResponse<Object> nithiumHttpResponse = new NithiumHttpResponse<>(response, object);
            if (nithiumHttpResponse.response().getCode() != 200) throw new NithiumException(nithiumHttpResponse.response().getReasonPhrase());

            return nithiumHttpResponse;
        } catch (IOException e) {
            throw new NithiumException(e.getLocalizedMessage());
        }
    }

    public boolean DELETE(DataType dataType, String url, String apiKey) throws NithiumException {
        String httpUrl = BASE_URL + dataType.getUrl() + url;

        HttpDelete httpDelete = new HttpDelete(httpUrl);
        httpDelete.setHeader("Accept", "application/json");
        httpDelete.addHeader("X_API_KEY", apiKey);

        try {
            CloseableHttpResponse response = httpClient.execute(httpDelete);

            if (response.getCode() != 200) throw new NithiumException(response.getReasonPhrase());

            return true;
        } catch (IOException e) {
            throw new NithiumException(e.getLocalizedMessage());
        }
    }

    public boolean DELETE(DataType dataType, String url) throws NithiumException {
        String httpUrl = BASE_URL + dataType.getUrl() + url;

        HttpDelete httpDelete = new HttpDelete(httpUrl);
        httpDelete.setHeader("Accept", "application/json");

        try {
            CloseableHttpResponse response = httpClient.execute(httpDelete);

            if (response.getCode() != 200) throw new NithiumException(response.getReasonPhrase());

            return true;
        } catch (IOException e) {
            throw new NithiumException(e.getLocalizedMessage());
        }
    }
}
