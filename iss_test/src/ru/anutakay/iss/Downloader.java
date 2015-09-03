package ru.anutakay.iss;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

public class Downloader {
    
    DownloadManager downloadManager;
    
    @SuppressLint("InlinedApi")
    public Downloader(Context context) {
        downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
    }
    
    public void download(String address) {
        
        Uri source = Uri.parse(address);    
        Uri destination = downloadLocation(source);
       
        Request request = new Request(source);
        request.setDestinationUri(destination);
        request.setTitle(source.toString());
        request.setMimeType("application/mp3");
        downloadManager.enqueue(request);
    };
    
    private Uri downloadLocation(Uri source) {
        File dir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS);
        dir.mkdirs();
        return Uri.fromFile(new File(dir, source.getLastPathSegment()));
    }

}
