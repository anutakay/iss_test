package ru.anutakay.iss;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends ListActivity 
                            implements LoaderCallbacks<List<Track>> {

    static final int LIST_LOADER_ID = 1;
    
    ListView listView;
    
    Adapter adapter;
    
    List<Track> tracks = new ArrayList<Track>();
    
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
        loader = new AsyncListLoader(this, args);
      } 
      APIClient apiClient = new APIClientImpl(this);
      loader.setApiClient(apiClient);
      return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<Track>> loader, 
                                        List<Track> data) {
        tracks.addAll(data);
        adapter.notifyDataSetChanged();     
    }

    @Override
    public void onLoaderReset(Loader<List<Track>> loader) {   
    }
    
    BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("Debug", "Получен интент" + intent);
        }
        
    };
    
    @SuppressLint("InlinedApi")
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED));
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    };
}
