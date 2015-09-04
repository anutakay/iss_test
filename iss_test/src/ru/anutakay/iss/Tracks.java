package ru.anutakay.iss;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

public class Tracks {
    
    public Context context;
    
    public List<Track> tracks;

    public Tracks() {
        tracks = new ArrayList<Track>();
    }

    public int size() {
        return tracks.size();
    }

    public Track get(int position) {
        return tracks.get(position);
    }
    
    public void add(Track track) {
        tracks.add(track);
    }
    
    public void set(Tracks data) {
        tracks.clear();
        addAll(data);
    }
    
    public void addAll(Tracks data) {
        addAll(data.tracks);
    }

    public void addAll(List<Track> data) {
        tracks.addAll(data);
    }
}