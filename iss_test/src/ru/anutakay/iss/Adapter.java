package ru.anutakay.iss;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Adapter extends SimpleAdapter {
    
    final static int LAYOUT = R.layout.item;
    
    final static String[] FROM = { "track", "filename" };
    
    final static int[] TO = { R.id.text, R.id.text };
    

    public Adapter(Context context, List<Map<String, String>> data) {
        super(context, data, LAYOUT, FROM, TO);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        TextView text = (TextView)view.findViewById(R.id.text);
        
        final Map<String, String> item = (Map<String, String>)getItem(position);
        text.setText(item.get("filename"));
        if (item.get("filename") != null) {
            text.setText(item.get("filename"));
        } else {
            text.setText(item.get("track"));
            
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    item.put("filename", "\nololo\n");
                    Adapter.this.notifyDataSetChanged();
                }
            }, 1000);
        }
        return view;
    }
}
