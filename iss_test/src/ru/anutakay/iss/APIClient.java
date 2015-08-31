package ru.anutakay.iss;

public interface APIClient {
    
    String[] getListOfTracks();
    
    String loadTrackAndGetNameOfFile(String address);
}
