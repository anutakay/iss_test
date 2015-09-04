package ru.anutakay.iss;

import android.content.Context;

public class AsyncListLoader extends AsyncLoader<Tracks> {

    public AsyncListLoader(Context context) {
        super(context);
    }

    @Override
    public Tracks loadInBackground() {
        try {
            return apiClient.getTracks();
        } catch (APIException e) {
            e.printStackTrace();
            return new Tracks();
        }
    }
}