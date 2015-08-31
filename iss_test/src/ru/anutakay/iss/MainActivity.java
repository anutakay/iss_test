package ru.anutakay.iss;

import android.app.ListActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity implements LoaderCallbacks<String[]> {

    static final int LOADER_LIST_ID = 1;
    
    ListView listView;
    
    ArrayAdapter<String> adapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bndl = new Bundle();
        getLoaderManager().initLoader(LOADER_LIST_ID, bndl, this)
        .forceLoad();
    }
    
    @Override
    public Loader<String[]> onCreateLoader(int id, Bundle args) {
      XMLAsyncLoader loader = null;
      if (id == LOADER_LIST_ID) {
        loader = new XMLAsyncLoader(this, args);
        loader.setApiClient(new APIClientImpl(this));
      }
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
