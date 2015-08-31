package ru.anutakay.iss;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientImpl implements HttpClient {
    
    @Override
    public String getXML(String address) {
            HttpResponse httpResponse = getHttpResponse(address);
            if(httpResponse == null) { return ""; }
            
            HttpEntity httpEntity = httpResponse.getEntity();
            if(httpEntity == null) { return ""; }
            
            return transformToString(httpEntity);
    }
    
    private static HttpResponse getHttpResponse(String address) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(address);
        try {
           return httpClient.execute(httpGet);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }  

    private static String transformToString(HttpEntity httpEntity) {
        try {
            return EntityUtils.toString(httpEntity, "UTF-8");
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }  
}
