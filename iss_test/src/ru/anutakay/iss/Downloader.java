package ru.anutakay.iss;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

public class Downloader {
    
    DownloadManager downloadManager;
    
    @SuppressLint("InlinedApi")
    public Downloader(Context context) {
        downloadManager = (DownloadManager) context.getSystemService(
                                                        Context.DOWNLOAD_SERVICE);
    }  
    
    public void downloadIfMissing(Track track) {
        if(!track.isExist()) {
            Uri source = track.getSource();
            Uri destination = track.getDestination();
            download(source, destination);
        } else {
            Log.d("Debug", "Файл " + track.getTitle() + " уже существует");
        }
    }

    public void download(Uri source, Uri destination) {      
        Request request = new Request(source);
        request.setDestinationUri(destination);
        request.setTitle(source.toString());
        request.setMimeType("application/mp3");
        downloadManager.enqueue(request);
    };

}
