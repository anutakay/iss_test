package ru.anutakay.iss;

import wseemann.media.FFmpegMediaMetadataRetriever;
import android.annotation.SuppressLint;
import android.content.Context;

class TrackTitleDelegateImpl implements TrackTitleDelegate {
    
    private Context context;
    
    public TrackTitleDelegateImpl(Context context) {
        this.context = context;
    }
    
    @Override
    public String getProgressTitle(Track track) {
        return track.getSource().toString();
    }
    
    @Override
    public String getTitle(Track track) {
        if(track.fileExist()) {
            return getNameFromMetadata(track);
        } else {
            return track.getSource().toString();
        }
    }
    
    @SuppressLint("InlinedApi")
    private String getNameFromMetadata(Track track) {
        FFmpegMediaMetadataRetriever retriever = new FFmpegMediaMetadataRetriever(); 
        retriever.setDataSource(context, track.getDestination()); 
        String title = retriever.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_TITLE);
        retriever.release();
        return title;
    }
    
}
