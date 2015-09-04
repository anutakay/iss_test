package ru.anutakay.iss;

public interface APIClient {

    Tracks getTracks() throws APIException;
    
}
