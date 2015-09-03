package ru.anutakay.iss;

public class APIException extends Exception {
    
    private static final long serialVersionUID = -7267377032849927562L;

    public APIException(Exception e) {
       super(e);
    }
}
