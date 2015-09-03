package ru.anutakay.iss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import android.content.Context;

public class APIClientImpl implements APIClient {
    
    String listofTracksUrl = "http://192.168.1.35/list.xml";

    Context context;
    
    HttpClient httpClient;
    
    NodeList nodes;
    
    List<Map<String, String>> results = new ArrayList<Map<String, String>>();
    
    public APIClientImpl(Context context) {
        this.context = context;
        httpClient = new HttpClientImpl();
    }
    
    @Override
    public List<Map<String, String>> getListOfTracks() throws APIException {
        try {
            String xml = httpClient.getXML(listofTracksUrl);
            Document document = Parser.parse(xml);
            return extractTracks(document);
        } catch (Exception e) {
            throw new APIException(e);
        }       
    }
    
    private List<Map<String, String>> extractTracks(Document document) {     
        nodes = document.getElementsByTagName("track");
        results.clear();
        int length = nodes.getLength();
        for(int i = 0; i < length; i++) {
            addTrack(i);
        }
        return results;
    }

    private void addTrack(int i) {
        HashMap<String, String> map = new HashMap<String, String>();
        String track = nodes.item(i).getTextContent();
        track = track.trim();
        map.put("track", track);
        results.add(map);
    }

    @Override
    public String loadTrackAndGetNameOfFile(String address) {
        // TODO Auto-generated method stub
        return null;
    }

}
