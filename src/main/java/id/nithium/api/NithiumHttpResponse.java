package id.nithium.api;

import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;

public record NithiumHttpResponse<T>(CloseableHttpResponse response, T obj) {

}
