package ru.anutakay.iss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.net.Uri;

public class Tracks {
    
    public List<Track> tracks;
    
    public Map<String, Integer> checked;

    public Tracks() {
        tracks = new ArrayList<Track>();
        checked = new HashMap<String, Integer>();
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
        checked.clear();
        addAll(data);
    }
    
    public void addAll(Tracks data) {
        addAll(data.tracks);
    }

    public void addAll(List<Track> data) {
        tracks.addAll(data);
    }

    public boolean checked(int position) {
        return checked.containsValue(position);
    }
    
    public void check(int position) {
        Track track = tracks.get(position);
        Uri uri = track.getSource();
        checked.put(uri.getLastPathSegment(), position);
    }
    
    public void uncheck(Uri uri) {
        String str = uri.getLastPathSegment();
        if(checked.containsKey(str)) {
            checked.remove(str);
        }  
    }
}