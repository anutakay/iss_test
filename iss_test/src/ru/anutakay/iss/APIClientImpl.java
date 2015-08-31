package ru.anutakay.iss;

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
    public String[] getListOfTracks() {
        String xml = httpClient.getXML("http://192.168.1.35/list.txt");
        Document document = Parser.parse(xml);
        return parseDocument(document);
    }
    
    private String[] parseDocument(Document document) {
        NodeList nodes = document.getElementsByTagName("track");
        int length = nodes.getLength();
        String[] results = new String[length];
        for(int i = 0; i < length; i++) {
            results[i] = nodes.item(i).getTextContent();
        }
        return results;
    }

    @Override
    public String loadTrackAndGetNameOfFile(String address) {
        // TODO Auto-generated method stub
        return null;
    }

}
