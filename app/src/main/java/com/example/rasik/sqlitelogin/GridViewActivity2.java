package com.example.rasik.sqlitelogin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by rasik on 19/9/17.
 */

public class GridViewActivity2 extends AppCompatActivity {
    GridView grid;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_2);
        sqLiteHelper = new SQLiteHelper(this);

        getData();



    }

    public void getData(){

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM UserTable",null);
        if(cursor.moveToFirst())
        {
            ArrayList<String> arrayList = new ArrayList<>();
            ArrayList<String> arrayList1 = new ArrayList<>();

            do
            {
                arrayList.add("Name: "+cursor.getString(cursor.getColumnIndex("name")));
                arrayList1.add("Email: "+cursor.getString(cursor.getColumnIndex("email")));

            }while(cursor.moveToNext());
            System.out.println();
            String[] name = new String[arrayList.size()];
            String[] email = new String[arrayList1.size()];
            name = arrayList.toArray(name);
            email = arrayList1.toArray(email);
            CustomGrid adapter = new CustomGrid(GridViewActivity2.this, name, email);
            grid=(GridView)findViewById(R.id.grid);
            grid.setAdapter(adapter);

        }
        else
        {
            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }
}