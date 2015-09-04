package ru.anutakay.iss;

import java.io.File;

import wseemann.media.FFmpegMediaMetadataRetriever;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;

public class Track {
    
    private Uri source;
    
    File dir;
    
    public Track(String uri, File dir) {     
        source = Uri.parse(uri);
        this.dir = dir;
    }
    
    public String getTitle(Context context) {
        if(isExist()) {
            return getNameFromMetadata(context);
        } else {
            return source.toString();
        }
    }
    
    @SuppressLint("InlinedApi")
    private String getNameFromMetadata(Context context) {
        FFmpegMediaMetadataRetriever retriever = new FFmpegMediaMetadataRetriever(); 
        retriever.setDataSource(context, this.getDestination()); 
        String title = retriever.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_TITLE);
        retriever.release();
        return title;
    }
    
    public String getProgressTitle() {
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