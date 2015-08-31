package ru.anutakay.iss;

import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends ListActivity 
                            implements LoaderCallbacks<List<Map<String, String>>> {

    static final int LIST_LOADER_ID = 1;
    static final int FILE_LOADER_ID = 2;
    
    ListView listView;
    
    Adapter adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager().initLoader(LIST_LOADER_ID, null, this)
        .forceLoad();
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
      AsyncLoader loader = null;
      if (id == LIST_LOADER_ID) {
        loader = new AsyncListLoader(this, args);
      } if (id == FILE_LOADER_ID) {
        loader = new AsyncFileLoader(this, args);  
      }
      loader.setApiClient(new APIClientImpl(this));
      return loader;
    }

    @Override
    public void onLoadFinished(Loader<List<Map<String, String>>> loader, 
                                        List<Map<String, String>> data) {
        String[] from = { "track" };
        int[] to = { R.id.text };
        adapter = new Adapter(this, data, R.layout.item, from, to);
        setListAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<List<Map<String, String>>> loader) {   
    }
}
