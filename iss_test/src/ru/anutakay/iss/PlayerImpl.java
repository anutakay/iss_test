package ru.anutakay.iss;

import android.util.Log;

public class PlayerImpl implements Player {
    
    public PlayerImpl() {
        
    }
    
    @Override
    public void playPause(Track track) {
        Log.d("Debug", "" + track);
    }
}
