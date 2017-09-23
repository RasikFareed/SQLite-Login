package com.example.rasik.sqlitelogin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static com.example.rasik.sqlitelogin.R.id.gridView;

/**
 * Created by rasik on 19/9/17.
 */

public class HorizontalViewActivity extends AppCompatActivity {
    private RecyclerView vertical_recycler_view,horizontal_recycler_view;
    private ArrayList<String> horizontalList,verticalList;
    private HorizontalAdapter horizontalAdapter;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String name,email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_view);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        sqLiteHelper = new SQLiteHelper(this);
        name="";
        email="";
        getData();

    }

    public void getData(){
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM UserTable",null);
        if(cursor.moveToFirst())
        {  horizontalList=new ArrayList<>();
            do
            {
                name=cursor.getString(cursor.getColumnIndex("name"));
                email=cursor.getString(cursor.getColumnIndex("email"));
                horizontalList.add("Name: "+name+"\n"+"Email: "+email);


            }while(cursor.moveToNext());

            horizontalAdapter=new HorizontalAdapter(horizontalList);

            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(HorizontalViewActivity.this, LinearLayoutManager.HORIZONTAL, false);
            horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

            horizontal_recycler_view.setAdapter(horizontalAdapter);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No data found", Toast.LENGTH_LONG).show();
        }
        cursor.close();

    }

    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

        private List<String> horizontalList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtView;

            public MyViewHolder(View view) {
                super(view);
                txtView = (TextView) view.findViewById(R.id.txtView);

            }
        }


        public HorizontalAdapter(List<String> horizontalList) {
            this.horizontalList = horizontalList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.horizontal_single_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.txtView.setText(horizontalList.get(position));


        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }
    }



}
