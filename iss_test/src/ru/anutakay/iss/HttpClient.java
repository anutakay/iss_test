package ru.anutakay.iss;

import java.io.IOException;

public interface HttpClient {

    String get(String address) throws IOException;

}