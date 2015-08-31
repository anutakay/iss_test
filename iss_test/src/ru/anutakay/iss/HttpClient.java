package ru.anutakay.iss;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public class HttpClient {
    
    public static String getXML(String address) {
        String line = "";
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(address);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            if(httpEntity!=null) {
                line = EntityUtils.toString(httpEntity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public void getXMLAsync(String address, String params) {
        getXMLAsyncTask task = new getXMLAsyncTask();
        task.execute(address, params);
    }
    
    private class getXMLAsyncTask extends AsyncTask<Object, Object, String> {
        String address = null;
        
        @Override
        protected String doInBackground(Object... params) {
            address = (String)params[0];
            return getXML(address);
        }
        
        @Override
        protected void onPostExecute(String result) {
        }
    };
}
