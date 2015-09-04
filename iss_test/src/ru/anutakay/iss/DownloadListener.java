package ru.anutakay.iss;

public interface DownloadListener {
    
    void onLoadFinished(Tracks data);
    
    void onFileLoadFinished(String uriString);
    
}
