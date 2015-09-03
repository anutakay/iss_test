package ru.anutakay.iss;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;

public class APIClientImpl implements APIClient {
    
    String listofTracksUrl = "http://192.168.1.35/list.xml";

    Context context;
    
    HttpClient httpClient;
    
    public APIClientImpl(Context context) {
        this.context = context;
        httpClient = new HttpClientImpl();
    }
    
    @Override
    public List<Track> getListOfTracks() throws APIException {
        try {
            String xml = httpClient.getXML(listofTracksUrl);
            Document document = Parser.parse(xml);
            return extractTracks(document);
        } catch (Exception e) {
            throw new APIException(e);
        }       
    }
    
    private List<Track> extractTracks(Document document) {   
        NodeList nodes = document.getElementsByTagName("track");   
        List<Track> results = new ArrayList<Track>();

        int length = nodes.getLength();
        for(int i = 0; i < length; i++) {
            Node node = nodes.item(i);
            String uri = node.getTextContent();
            Track track = new Track(uri.trim()); 
            results.add(track);
        }
        return results;
    }


    @Override
    public String loadTrackAndGetNameOfFile(String address) {
        // TODO Auto-generated method stub
        return null;
    }

}
