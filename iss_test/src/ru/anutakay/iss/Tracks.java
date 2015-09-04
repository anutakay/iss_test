package ru.anutakay.iss;

import java.util.ArrayList;
import java.util.List;

public class Tracks {
    
    public List<Track> tracks;
    
    public List<Integer> checked;

    public Tracks() {
        tracks = new ArrayList<Track>();
        checked = new ArrayList<Integer>();
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
        return checked.contains(position);
    }
    
    public void check(int position) {
        checked.add(position);
    }
}