package ru.anutakay.iss;

import java.util.List;
import java.util.Map;

public interface APIClient {
    
    String loadTrackAndGetNameOfFile(String address) throws APIException;

    List<Map<String, String>> getListOfTracks() throws APIException;
}
