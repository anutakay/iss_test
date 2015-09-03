package ru.anutakay.iss;

import java.util.List;

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
    

    public Adapter(Context context, List<Track> tracks) {
        super();
        this.context = context;
        this.tracks = tracks;
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
        String title = item.getTitle();
        text.setText(title);
        item.downloadIfNotExist(context);
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
