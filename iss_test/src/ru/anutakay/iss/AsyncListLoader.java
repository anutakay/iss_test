package ru.anutakay.iss;

import android.content.Context;
import android.os.Bundle;

public class AsyncListLoader extends AsyncLoader<String[]> {
    
    public AsyncListLoader(Context context, Bundle bundle) {
      super(context);
    }
    
    @Override
    public String[] loadInBackground() {
        return apiClient.getListOfTracks();
    }
}