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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends ListActivity implements LoaderCallbacks<Tracks> {

    static final int LIST_LOADER_ID = 1;
    
    DownloadListener downloadListener;
    
    BroadcastReceiver receiver;
    
    Player player;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);     
        TracksAdapter adapter = new TracksAdapter(this, R.layout.item);
        setListAdapter(adapter);
        receiver = new DownloadReceiver(adapter);
        downloadListener = adapter;
        
        player = new PlayerImpl();
        
        getListView().setOnItemClickListener(listener);
        
        LoaderManager loaderManager = getLoaderManager();
        Loader<Tracks> loader = loaderManager.initLoader(LIST_LOADER_ID, null, this);
        loader.forceLoad();
    }
    
    OnItemClickListener listener = new OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Track track = (Track)getListView().getAdapter().getItem(position);
            player.playPause(track);  
        }
    };
      
    @SuppressLint("InlinedApi")
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        player.resume();
    };

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
        player.pause();
    };
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.destroy();
    }
    
    @Override
    public Loader<Tracks> onCreateLoader(int id, Bundle args) {
      AsyncLoader<Tracks> loader = null;
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
}
