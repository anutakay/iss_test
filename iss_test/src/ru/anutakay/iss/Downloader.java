package ru.anutakay.iss;

import android.content.Context;
import android.util.Log;

public class Downloader {
    
    Context context;
    
    public Downloader(Context context) {
        this.context = context;
    }
    
    public void download(String url) {
        Log.d("Debug", "Скачать " + url);
    };

}
