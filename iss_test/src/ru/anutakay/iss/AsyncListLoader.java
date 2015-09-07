package ru.anutakay.iss;

import android.content.Context;

public class AsyncListLoader extends AsyncLoader<Tracks> {

    public AsyncListLoader(Context context) {
        super(context);
    }

    @Override
    public Tracks loadInBackground() {
        if(getApiClient() == null) {
            return new Tracks();
        }
        try {
            return getApiClient().getTracks();
        } catch (APIException e) {
            e.printStackTrace();
            return new Tracks();
        }
    }
}