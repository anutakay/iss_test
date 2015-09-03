package ru.anutakay.iss;

import java.util.List;
import java.util.Map;

public interface APIClient {
    
    List<Map<String, String>> getListOfTracks() throws APIException;
    
    String loadTrackAndGetNameOfFile(String address) throws APIException;
}
