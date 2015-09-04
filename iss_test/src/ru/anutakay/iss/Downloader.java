package ru.anutakay.iss;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.net.Uri;

public class Downloader {
    
    DownloadManager downloadManager;
    
    @SuppressLint("InlinedApi")
    public Downloader(Context context) {
        downloadManager = (DownloadManager) context.getSystemService(
                                                        Context.DOWNLOAD_SERVICE);
    }  

    public void download(Track track) {  
        Uri source = track.getSource();
        Uri destination = track.getDestination();
        Request request = new Request(source);
        request.setDestinationUri(destination);
        request.setTitle(source.toString());
        request.setMimeType("application/mp3");
        downloadManager.enqueue(request);
    };

}
