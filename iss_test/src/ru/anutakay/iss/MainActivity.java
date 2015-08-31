package ru.anutakay.iss;

import android.support.v7.app.ActionBarActivity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity implements LoaderCallbacks<String> {

    static final int LOADER_LIST_ID = 1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bndl = new Bundle();
        getLoaderManager().initLoader(LOADER_LIST_ID, bndl, this)
        .forceLoad();
    }
   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
      Loader<String> loader = null;
      if (id == LOADER_LIST_ID) {
        loader = new XMLAsyncLoader(this, args);
        Log.d("Debug", "onCreateLoader: " + loader.hashCode());
      }
      return loader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.d("Debug", data);
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {   
    }
}
