package ru.anutakay.iss;

import java.io.File;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

public class Track {
    
    Uri source;
    
    File file;
    
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
    
    
    public void downloadIfNotExist(Context context) {
        Downloader downloader = new Downloader(context);        
        if(!file.exists()) {
            downloader.download(source, file);
        } else {
            Log.d("Debug", "Файл " + file.getName() + " уже существует");
        }
    }
    
    
}