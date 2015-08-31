package ru.anutakay.iss;

import android.content.AsyncTaskLoader;
import android.content.Context;

public abstract class AsyncLoader<T> extends AsyncTaskLoader<T> {

    protected APIClient apiClient;

    public AsyncLoader(Context context) {
        super(context);
    }

    public APIClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(APIClient apiClient) {
        this.apiClient = apiClient;
    }

}