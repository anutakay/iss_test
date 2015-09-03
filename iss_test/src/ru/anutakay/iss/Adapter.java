package ru.anutakay.iss;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter {
    
    final static int LAYOUT = R.layout.item;
    
    final static String[] FROM = { "track", "filename" };
    
    final static int[] TO = { R.id.text, R.id.text };
    
    private Context context;
    
    List<Track> tracks;
    List<Map<String, String>> data;
    
    private class Track {
        String address;
        String filename;
        boolean started;
        boolean exist;
    }

    public Adapter(Context context, List<Map<String, String>> data) {
        super();
        this.context = context;
        this.data = data;
        tracks = convert();
    }
    
    @Override
    public void notifyDataSetChanged() {
        tracks = convert();
        super.notifyDataSetChanged();
    }
    
    private List<Track> convert() {
        List<Track> result = new ArrayList<Track>();
        for(Map<String, String> item : data) {
            
           String address = item.get("track");
           String filename = item.get("filename");
           
           Track track = new Track();
           track.address = address;
           track.filename = filename;
           track.started = false;
           track.exist = false;
           
           result.add(track);
        }
        return result;
    }
    
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView  = inflater.inflate(LAYOUT, parent, false);
        }   
        rowView = this.bindView(rowView, position);      
        return rowView;
    }
    
    private View bindView(View view, int position) {
        TextView text = (TextView)view.findViewById(R.id.text);
        
        final Track item = getItem(position);

       if (item.exist) {
            text.setText(item.filename);
        }else if(!item.started) {
            String url = item.address;
            text.setText(url);
            item.started = true;
            new Downloader(context).downloadIfMissing(url);
        }
        return view;
    }

    @Override
    public int getCount() {
        return tracks.size();
    }

    @Override
    public Track getItem(int position) {
        return tracks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
