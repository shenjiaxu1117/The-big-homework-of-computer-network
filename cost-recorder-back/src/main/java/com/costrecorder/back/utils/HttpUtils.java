package com.costrecorder.back.utils;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

/**
 * @author yinzihang
 */
public class HttpUtils {
    public static String getResponse(String url, Map<String, String> params) throws IOException {
        CloseableHttpResponse response = null;
        String result = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URIBuilder builder = new URIBuilder(url);
            if(params != null){
                for(String key : params.keySet()){
                    builder.addParameter(key, params.get(key));
                }
            }
            URI uri = builder.build();

            HttpGet httpGet = new HttpGet(uri);
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode() == 200){
                result =  EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(response != null){
                response.close();
            }
        }

        return result;
    }
}
