package com.example.rasik.sqlitelogin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by rasik on 19/9/17.
 */

public class CustomGrid extends BaseAdapter {
    private Context mContext;
    private final String[] name;
    private final String[] email;

    public CustomGrid(Context c,String[] name,String[] email ) {
        mContext = c;
        this.email = email;
        this.name = name;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text1);
            TextView textView1 = (TextView)grid.findViewById(R.id.grid_text2);
            textView.setText(name[position]);
            textView1.setText(email[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
