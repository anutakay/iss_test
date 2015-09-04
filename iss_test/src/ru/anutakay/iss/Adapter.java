package ru.anutakay.iss;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Adapter extends BaseAdapter {
    
    final static int LAYOUT = R.layout.item;
    
    Context context;
    
    Tracks tracks;
    
    Downloader downloader;

    public Adapter(Context context, Tracks tracks) {
        super();
        this.context = context;
        this.tracks = tracks;
        downloader = new Downloader(context); 
    }
    
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view  = inflater.inflate(LAYOUT, parent, false);
        }   
        view = this.bindView(view, position);      
        return view;
    }
    
    private View bindView(View view, int position) {
        Track track = getItem(position);
        String title = track.getTitle();
        
        TextView text = (TextView)view.findViewById(R.id.text);
        text.setText(title);
        
        if(!tracks.checked(position)) { 
            tracks.check(position);
            downloader.downloadIfMissing(track);
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
