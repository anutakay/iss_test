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
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }
    
    public void download(String address) {
        Uri uri = Uri.parse(address);
        Request request = new Request(uri);
        request.setTitle(uri.toString());
        request.setMimeType("application/mp3");
        downloadManager.enqueue(request);
    };

}
