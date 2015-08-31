package ru.anutakay.iss;

import java.util.List;
import java.util.Map;

public interface APIClient {
    
    List<Map<String, String>> getListOfTracks();
    
    String loadTrackAndGetNameOfFile(String address);
}
