package ru.anutakay.iss;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;

public class AsyncListLoader extends AsyncLoader<List<Map<String, String>>> {
    
    public AsyncListLoader(Context context, Bundle bundle) {
      super(context);
    }

    @Override
    public List<Map<String, String>> loadInBackground() {
        try {
            return apiClient.getListOfTracks();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
}