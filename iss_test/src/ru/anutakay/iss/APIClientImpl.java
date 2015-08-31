package ru.anutakay.iss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.content.Context;

public class APIClientImpl implements APIClient {

    Context context;
    
    HttpClient httpClient;
    
    public APIClientImpl(Context context) {
        this.context = context;
        httpClient = new HttpClientImpl();
    }
    
    @Override
    public List<Map<String, String>> getListOfTracks() {
        String xml = httpClient.getXML("http://192.168.1.35/list.txt");
        Document document = Parser.parse(xml);
        return parseDocument(document);
    }
    
    private List<Map<String, String>> parseDocument(Document document) {
        NodeList nodes = document.getElementsByTagName("track");
        int length = nodes.getLength();
        List<Map<String, String>> results = new ArrayList<Map<String, String>>();
        for(int i = 0; i < length; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("track", nodes.item(i).getTextContent());
            results.add(map);
        }
        return results;
    }

    @Override
    public String loadTrackAndGetNameOfFile(String address) {
        // TODO Auto-generated method stub
        return null;
    }

}
