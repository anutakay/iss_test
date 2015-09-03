package ru.anutakay.iss;

import android.content.Context;

public class Track {
    
    private String address;
    
    public Track(String uri) {
        address = uri;
    }
    
    public String getTitle() {
        return address;
    }
    
    public void downloadIfNotExist(Context context) {
        new Downloader(context).downloadIfMissing(this.getTitle());
    }
    
    
}