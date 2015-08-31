package ru.anutakay.iss;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

public class XMLAsyncLoader extends AsyncTaskLoader<String[]> {
    
    APIClient apiClient;

    public XMLAsyncLoader(Context context, Bundle bundle) {
      super(context);
    }
    
    public APIClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(APIClient apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public String[] loadInBackground() {
        return apiClient.getListOfTracks();
    }
}