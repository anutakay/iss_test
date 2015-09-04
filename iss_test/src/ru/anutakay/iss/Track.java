package ru.anutakay.iss;

import java.io.File;

import android.net.Uri;

public class Track {
    
    private Uri source;
    
    private File dir;
    
    public Track(String uri, File dir) {     
        source = Uri.parse(uri);
        this.dir = dir;
    }
    
    public boolean fileExist() {
        return getFile().exists();
    }   
    
    public Uri getSource() {
        return source;
    }
    
    public Uri getDestination() {
        return Uri.fromFile(getFile());
    }
    
    private File getFile() {
        return new File(dir, source.getLastPathSegment());
    }
}