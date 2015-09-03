package ru.anutakay.iss;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.util.Log;

public class Parser {

    public static Document parse(String xml) throws ParserException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = createDocumentBuilder(dbf);      
        Document document = createDocument(xml, db);
        return document;
    }

    private static Document createDocument(String xml, DocumentBuilder db)  throws ParserException {   
        ByteArrayInputStream is = createInputStream(xml);     
        return createDocumentFromInputStream(db, is);
    }
    
    private static ByteArrayInputStream createInputStream(String xml) throws ParserException {
        try {
            return new ByteArrayInputStream(xml.getBytes("UTF8"));
        } catch (UnsupportedEncodingException e) {
            throw new ParserException(e); 
        }
    }

    private static Document createDocumentFromInputStream(
                    DocumentBuilder db, ByteArrayInputStream is) throws ParserException {
        try {            
            return db.parse(is);
        } catch (SAXException e) {
            Log.d("Debug", "Wrong XML file structure: " + e.getMessage());
            throw new ParserException(e);
        } catch (IOException e) {
            Log.d("Debug", "I/O exeption: " + e.getMessage());
            throw new ParserException(e);
        }
    }

    private static DocumentBuilder createDocumentBuilder(DocumentBuilderFactory dbf) throws ParserException {
        try {
            return dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            Log.d("Debug", "XML parse error: " + e.getMessage());
            throw new ParserException(e);
        }
    }
}
