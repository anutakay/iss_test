package ru.anutakay.iss;

import java.io.IOException;

public interface HttpClient {

    String getXML(String address) throws IOException;

}