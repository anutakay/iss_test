package ru.anutakay.iss;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.widget.SimpleAdapter;

public class Adapter extends SimpleAdapter {
    
    final static int LAYOUT = R.layout.item;
    
    final static String[] FROM = { "track" };
    
    final static int[] TO = { R.id.text };
    

    public Adapter(Context context, List<Map<String, String>> data) {
        super(context, data, LAYOUT, FROM, TO);
    }
    
    //TODO - перегрузить нужные функции для того, чтобы показывалось название если есть 

   /* @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }*/

}
