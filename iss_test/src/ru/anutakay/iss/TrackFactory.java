package ru.anutakay.iss;

import java.io.File;

import android.content.Context;
import android.os.Environment;

public class TrackFactory {
    
    File dir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS); 
    
    Context context;
    
    public TrackFactory (Context context) {
        this.context = context;
        dir.mkdirs();
    }
    
    public Track makeTrack(String address) {
        Track track = new Track(address, dir);
        return track;     
    }

}
