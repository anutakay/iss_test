package ru.anutakay.iss;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends ListActivity implements LoaderCallbacks<Tracks> {

    static final int LIST_LOADER_ID = 1;
    
    ListView listView;
    
    Adapter adapter;
    
    Tracks tracks = new Tracks();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new Adapter(this, tracks);
        setListAdapter(adapter);
        getLoaderManager()
            .initLoader(LIST_LOADER_ID, null, this)
            .forceLoad();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
      AsyncLoader loader = null;
      if (id == LIST_LOADER_ID) {
        loader = new AsyncListLoader(this);
      } 
      APIClient apiClient = new APIClientImpl(this);
      loader.setApiClient(apiClient);
      return loader;
    }

    @Override
    public void onLoadFinished(Loader<Tracks> loader, Tracks data) {
        tracks.set(data);
        adapter.notifyDataSetChanged();     
    }

    @Override
    public void onLoaderReset(Loader<Tracks> loader) {   
    }
    
    BroadcastReceiver receiver = new BroadcastReceiver() {

        @SuppressLint("InlinedApi")
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            
            Log.d("Debug", ""+intent);

            //Сообщение о том, что загрузка закончена
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
                        tracks.uncheck(Uri.parse(uriString));
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
        
    };
    
    @SuppressLint("InlinedApi")
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    };
}
