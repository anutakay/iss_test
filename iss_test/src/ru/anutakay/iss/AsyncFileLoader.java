package ru.anutakay.iss;

import android.content.Context;
import android.os.Bundle;

public class AsyncFileLoader extends AsyncLoader<String> {
 
    String address;

    public AsyncFileLoader(Context context, Bundle args) {
        super(context);
        this.address = args.getString("address");
    }

    @Override
    public String loadInBackground() {    
        try {
            return apiClient.loadTrackAndGetNameOfFile(address);
        } catch (APIException e) {
            e.printStackTrace();
            return "";
        }
    }

}
