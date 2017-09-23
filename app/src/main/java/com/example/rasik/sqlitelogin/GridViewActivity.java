package com.example.rasik.sqlitelogin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by rasik on 19/9/17.
 */

public class GridViewActivity extends AppCompatActivity {

    private GridView gridView;
    private ArrayList<String> list;
    private ArrayAdapter<String> adapter;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String name,email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview);
        sqLiteHelper = new SQLiteHelper(this);
        gridView = (GridView)findViewById(R.id.gridView);
        list = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,list);
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

                list.add(name);
                list.add(email);
                gridView.setBackgroundColor(Color.parseColor("#22AB29FF"));
                gridView.setAdapter(adapter);
            }while(cursor.moveToNext());
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }
}
