package ru.anutakay.iss;

import java.io.File;

import android.net.Uri;

public class Track {
    
    private Uri source;
    
    private File file;
    
    public Track(String uri, File dir) {     
        source = Uri.parse(uri);
        file = new File(dir, source.getLastPathSegment());
    }
    
    public String getTitle() {
        if(file.exists()) {
            return file.getName();
        } else {
            return source.toString();
        }
    }
    
    public boolean isExist() {
        return file.exists();
    }   
    
    public Uri getSource() {
        return source;
    }
    
    public Uri getDestination() {
        return Uri.fromFile(file);
    }
    
    public File getFile() {
        return file;
    }
}