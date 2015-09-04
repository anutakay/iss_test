package ru.anutakay.iss;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TracksAdapter extends BaseAdapter implements DownloadListener {
    
    private int layout;
    
    private Tracks tracks;
    
    private Downloader downloader;
    
    private TrackTitleDelegate namer;
    
    private LayoutInflater inflater;
    
    public TracksAdapter(Context context, int layout) {
        this.layout = layout;
        tracks = new Tracks();
        downloader = new Downloader(context); 
        namer = new TrackTitleDelegateImpl(context);
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            view  = inflater.inflate(layout, parent, false);
        }   
        view = this.bindView(view, position);      
        return view;
    }
    
    private View bindView(View view, int position) {
        TextView text = (TextView)view.findViewById(R.id.text);
        
        Track track = getItem(position);
        boolean inProgress = tracks.checked(position);
        
        if(inProgress) { 
            text.setText(namer.getProgressTitle(track));       
        } else {
            text.setText(namer.getTitle(track));
            downloadIfMissing(position);
        }
        return view;
    }

    private void downloadIfMissing(int position) {
        Track track = getItem(position);
        boolean downloaded = track.fileExist();
        
        if(!downloaded) {
            downloader.download(track);
            tracks.check(position);
            notifyAsync();
        } 
    }

    private void notifyAsync() {
        Handler h = new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
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
    public void onFileLoadFinished(String uriString) {
        tracks.uncheck(Uri.parse(uriString)); 
        notifyAsync();
    }

    @Override
    public void onLoadFinished(Tracks data) {
        tracks.set(data);
        notifyAsync();
    }
 
}
