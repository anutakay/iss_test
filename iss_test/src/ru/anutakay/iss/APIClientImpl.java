package ru.anutakay.iss;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;

public class APIClientImpl implements APIClient {
    
    String listofTracksUrl = "http://192.168.1.35/list.xml";

    Context context;
    
    HttpClient httpClient;
    
    TrackFactory factory;
    
    public APIClientImpl(Context context) {
        this.context = context;
        this.factory = new TrackFactory(context);
        httpClient = new HttpClientImpl();      
    }
    
    @Override
    public Tracks getTracks() throws APIException {
        try {
            String xml = httpClient.getXML(listofTracksUrl);
            Document document = Parser.parse(xml);
            return extractTracks(document);
        } catch (Exception e) {
            throw new APIException(e);
        }       
    }
    
    private Tracks extractTracks(Document document) {   
        NodeList nodes = document.getElementsByTagName("track");   
        Tracks results = new Tracks();
        
        int length = nodes.getLength();
        for(int i = 0; i < length; i++) {
            Node node = nodes.item(i);
            String uri = node.getTextContent().trim();
            Track track = factory.makeTrack(uri);
            results.add(track);
        }
        return results;
    }

}
