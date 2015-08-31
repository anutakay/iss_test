package ru.anutakay.iss;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity implements LoaderCallbacks<String[]> {

    static final int LIST_LOADER_ID = 1;
    static final int FILE_LOADER_ID = 2;
    
    ListView listView;
    
    ArrayAdapter<String> adapter;
    
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
    public void onLoadFinished(Loader<String[]> loader, String[] data) {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<String[]> loader) {   
    }
}
