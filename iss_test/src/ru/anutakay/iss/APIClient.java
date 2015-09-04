package ru.anutakay.iss;

import java.util.List;

public interface APIClient {

    List<Track> getListOfTracks() throws APIException;
}
