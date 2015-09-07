package ru.anutakay.iss;

import java.net.URL;
import java.util.Scanner;

import android.content.Context;

public class TextApiClient implements APIClient {
    
    String listofTracksUrl;

    Context context;
    
    HttpClient httpClient;
    
    TrackFactory factory;
    
    public TextApiClient(Context context) {
        this.context = context;
        this.factory = new TrackFactory(context);
        httpClient = new HttpClientImpl();   
        listofTracksUrl = context.getString(R.string.api_list_txt);
    }

    @Override
    public Tracks getTracks() throws APIException {

            Scanner s = null;
            try {
                URL url = new URL(listofTracksUrl);
                s = new Scanner(url.openStream());
            } catch (Exception e) {
                throw new APIException(e);
            }
            return extractTracks(s);       
    }

    private Tracks extractTracks(Scanner scanner) {
        Tracks results = new Tracks();
        
        String uri; 
        scanner.useDelimiter("\n");
        while (scanner.hasNext()) {
            uri = scanner.next().trim();
            Track track = factory.makeTrack(uri);
            results.add(track);
        }      
        scanner.close();
        return results;
    }

}
