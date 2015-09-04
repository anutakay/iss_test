package ru.anutakay.iss;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;

public class PlayerImpl implements Player {
    
    Track lastTrack;
    
    private MediaPlayer mp;
    
    public PlayerImpl() {
        mp = new MediaPlayer();
    }
    
    @Override
    public void playPause(Track track) {
        if(lastTrack != null && lastTrack.equals(track)) {
            if(mp.isPlaying()) {
                mp.stop();
            } else {
                prepare(track);
            }
        } else {
            prepare(track);
        }     
        lastTrack = track;
    }

    private void prepare(Track track) {
        mp.reset();
        try {
        mp.setDataSource(track.getSource().toString());
        mp.setOnPreparedListener(preparedListener);
        mp.prepare();
      } catch (IllegalArgumentException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (SecurityException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IllegalStateException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
    OnPreparedListener preparedListener = new OnPreparedListener() {

        @Override
        public void onPrepared(MediaPlayer mp) {
            mp.start();
        }
    };
    
    boolean playing;

    @Override
    public void pause() {
        if(mp.isPlaying()) {
            mp.pause();
            playing = true;
        }
    }

    @Override
    public void resume() {
        if(playing) {
            mp.start();
        }
        playing = false;
    }

    @Override
    public void destroy() {
        mp.release();
    }
}
