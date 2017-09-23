package com.example.rasik.sqlitelogin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by rasik on 19/9/17.
 */

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<HashMap<String,String>> list;
    private ArrayAdapter<HashMap<String,String>> adapter;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String name,email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        sqLiteHelper = new SQLiteHelper(this);
        listView = (ListView)findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new ArrayAdapter<HashMap<String,String>>(getApplicationContext(),android.R.layout.simple_list_item_1,android.R.id.text1,list);
        name="";
        email="";
        getData();

    }

    public void getData(){

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM UserTable",null);
        if(cursor.moveToFirst())
        {

            do
            {
                name=cursor.getString(cursor.getColumnIndex("name"));
                email=cursor.getString(cursor.getColumnIndex("email"));
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("name","Name:"+name);
                hashMap.put("email","Email: "+email);
                list.add(hashMap);
                // listView.setAdapter(adapter);
            }while(cursor.moveToNext());
                System.out.println(list);
            String[] from ={"name","email"};
            int[] to = {R.id.textView1,R.id.textView2};
            SimpleAdapter simpleAdapter=new SimpleAdapter(this,list,R.layout.activity_list_view_items,from,to);
            listView.setBackgroundColor(Color.parseColor("#22AB29FF"));
            listView.setAdapter(simpleAdapter);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }
}
