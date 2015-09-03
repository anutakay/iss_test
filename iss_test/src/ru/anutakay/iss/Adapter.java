package ru.anutakay.iss;

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
    
    List<Map<String, String>> data;

    public Adapter(Context context, List<Map<String, String>> data) {
        super();
        this.context = context;
        this.data = data;
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
    
    @SuppressWarnings("unchecked")
    private View bindView(View view, int position) {
        TextView text = (TextView)view.findViewById(R.id.text);
        
        final Map<String, String> item = (Map<String, String>)getItem(position);

        if (item.get("exist") != null) {
            text.setText(item.get("filename"));
        } else {
            String url = item.get("track");
            text.setText(url);
            item.put("exist", "true");
            new Downloader(context).downloadIfMissing(url);
        }
        return view;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
