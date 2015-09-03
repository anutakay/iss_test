package ru.anutakay.iss;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class Downloader {
    
    DownloadManager downloadManager;
    
    @SuppressLint("InlinedApi")
    public Downloader(Context context) {
        downloadManager = (DownloadManager) context.getSystemService(
                                                        Context.DOWNLOAD_SERVICE);
    }
    
    public void downloadIfMissing(String address) {      
        Uri source = Uri.parse(address);
        File file = downloadLocation(source);
        if(!file.exists()) {
            download(source, file);
        } else {
            Log.d("Debug", "Файл " + file.getName() + " уже существует");
        }
    }
    
    private File downloadLocation(Uri source) {
        File dir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS);
        dir.mkdirs();
        return new File(dir, filename(source));
    }
    
    private String filename(Uri source) {
        return source.getLastPathSegment();
    }

    private void download(Uri source, File file) {
        Uri destination = Uri.fromFile(file);
        Request request = new Request(source);
        request.setDestinationUri(destination);
        request.setTitle(source.toString());
        request.setMimeType("application/mp3");
        downloadManager.enqueue(request);
    };

}
