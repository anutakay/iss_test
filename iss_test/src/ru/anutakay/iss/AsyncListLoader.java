package ru.anutakay.iss;

import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.os.Bundle;

public class AsyncListLoader extends AsyncLoader<List<Track>> {
    
    public AsyncListLoader(Context context, Bundle bundle) {
      super(context);
    }

    @Override
    public List<Track> loadInBackground() {
        try {
            return apiClient.getListOfTracks();
        } catch (APIException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}