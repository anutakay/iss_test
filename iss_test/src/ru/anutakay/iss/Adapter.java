package ru.anutakay.iss;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
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
        TextView text = (TextView)view.findViewById(R.id.text);
        
        Track track = getItem(position);
        boolean inProgress = tracks.checked(position);
        
        if(inProgress) { 
            text.setText(track.getProgressTitle());       
        } else {
            text.setText(track.getTitle());
            downloadIfMissing(position);
        }
        return view;
    }

    private void downloadIfMissing(int position) {
        Track track = getItem(position);
        if(!track.isExist()) {
            downloader.download(track);
            tracks.check(position);
            Handler h = new Handler();
            h.post(new Runnable() {

                @Override
                public void run() {
                    Adapter.this.notifyDataSetChanged();
                }
            });
        } 
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
    
    @Override
    public void notifyDataSetChanged() {
        Log.d("Debug", "notifyDataSetChanged");
        super.notifyDataSetChanged();
    }
 
}
