package ru.anutakay.iss;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

public class DownloadReceiver extends BroadcastReceiver {
    
    DownloadListener downloadListener;
    
    public DownloadReceiver(DownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }
    
    @SuppressLint("InlinedApi")
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)) {
            long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
            DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Query query = new DownloadManager.Query();          
            query.setFilterById(downloadId);
            Cursor cursor = dm.query(query);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(columnIndex)) {                      
                    String uriString = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    downloadListener.onFileLoadFinished(uriString);
                }
            }
        }
    }      
}
