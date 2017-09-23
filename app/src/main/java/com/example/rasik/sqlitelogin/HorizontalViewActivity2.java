package com.example.rasik.sqlitelogin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.rasik.sqlitelogin.R.id.grid;

/**
 * Created by rasik on 20/9/17.
 */

public class HorizontalViewActivity2 extends AppCompatActivity {
    private LinearLayout mainLayout;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;


    private String[] names = {"rasik","rahul","kani","mani"};
    private View cell;
    private TextView cell_text1,cell_text2;


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        sqLiteHelper = new SQLiteHelper(this);
        setContentView(R.layout.activity_new_horizontal_scroll);

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
            String[] names = new String[arrayList.size()];
            String[] email = new String[arrayList1.size()];
            names = arrayList.toArray(names);
            email = arrayList1.toArray(email);

            mainLayout = (LinearLayout) findViewById(R.id._linearLayout);

            for (int i = 0; i < names.length; i++) {

                cell = getLayoutInflater().inflate(R.layout.cell, null);

                cell_text1 = (TextView) cell.findViewById(R.id.cell_text1);
                cell_text2 = (TextView) cell.findViewById(R.id.cell_text2);

                cell_text1.setText(names[i]);
                cell_text2 .setText(email[i]+" ");

                mainLayout.addView(cell);
            }

        }
        else
        {
            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
        }
        cursor.close();
    }

}