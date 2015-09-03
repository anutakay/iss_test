package ru.anutakay.iss;

import java.util.List;

public interface APIClient {
    
    String loadTrackAndGetNameOfFile(String address) throws APIException;

    List<Track> getListOfTracks() throws APIException;
}
