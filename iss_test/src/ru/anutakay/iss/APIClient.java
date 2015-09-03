package ru.anutakay.iss;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface APIClient {
    
    List<Map<String, String>> getListOfTracks() throws IOException;
    
    String loadTrackAndGetNameOfFile(String address);
}
