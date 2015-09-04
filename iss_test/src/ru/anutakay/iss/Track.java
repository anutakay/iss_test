package ru.anutakay.iss;

import java.io.File;

import android.net.Uri;
import android.util.Log;

public class Track {
    
    private Uri source;
    
    File dir;
    
    public Track(String uri, File dir) {     
        source = Uri.parse(uri);
        this.dir = dir;
    }
    
    public String getTitle() {
        Log.d("Debug", "getTitle");
        if(isExist()) {
            return getFile().getName();
        } else {
            return source.toString();
        }
    }
    
    public String getProgressTitle() {
        Log.d("Debug", "getProgressTitle");
        return source.toString();
    }
    
    public boolean isExist() {
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