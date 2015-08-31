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

    public static Document parse(String xml) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        if(dbf == null) { return null; }
        
        DocumentBuilder db = createDocumentBuilder(dbf);
        if(db == null) { return null; }
        
        Document document = createDocument(xml, db);
        return document;
    }

    private static Document createDocument(String xml, DocumentBuilder db) {   
        ByteArrayInputStream is = createInputStream(xml);
        if(is == null) { return null; }
        
        return createDocumentFromInputStream(db, is);
    }
    
    private static ByteArrayInputStream createInputStream(String xml) {
        try {
            return new ByteArrayInputStream(xml.getBytes("UTF8"));
        } catch (UnsupportedEncodingException e1) {
            return null;
        }
    }

    private static Document createDocumentFromInputStream(DocumentBuilder db, 
                                                            ByteArrayInputStream is) {
        try {            
            return db.parse(is);
        } catch (SAXException e) {
            Log.d("Debug", "Wrong XML file structure: " + e.getMessage());
            return null;
        } catch (IOException e) {
            Log.d("Debug", "I/O exeption: " + e.getMessage());
            return null;
        }
    }

    private static DocumentBuilder createDocumentBuilder(DocumentBuilderFactory dbf) {
        try {
            return dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            Log.d("Debug", "XML parse error: " + e.getMessage());
            return null;
        }
    }
}
