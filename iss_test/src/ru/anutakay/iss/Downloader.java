package ru.anutakay.iss;

import java.io.File;

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

    public void download(Uri source, File file) {
        Uri destination = Uri.fromFile(file);
        Request request = new Request(source);
        request.setDestinationUri(destination);
        request.setTitle(source.toString());
        request.setMimeType("application/mp3");
        downloadManager.enqueue(request);
    };

}
