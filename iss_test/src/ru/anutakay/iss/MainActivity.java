package ru.anutakay.iss;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Loader;
import android.os.Bundle;

public class MainActivity extends ListActivity implements LoaderCallbacks<Tracks> {

    static final int LIST_LOADER_ID = 1;
    
    DownloadListener downloadListener;
    
    BroadcastReceiver receiver;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     
        TracksAdapter adapter = new TracksAdapter(this, R.layout.item);
        setListAdapter(adapter);
        receiver = new DownloadReceiver(adapter);
        downloadListener = adapter;
        
        LoaderManager loaderManager = getLoaderManager();
        Loader<Tracks> loader = loaderManager.initLoader(LIST_LOADER_ID, null, this);
        loader.forceLoad();
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
        downloadListener.onLoadFinished(data);     
    }

    @Override
    public void onLoaderReset(Loader<Tracks> loader) {   
    }
    
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
