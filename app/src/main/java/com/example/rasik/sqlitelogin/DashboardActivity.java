package com.example.rasik.sqlitelogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rasik on 19/9/17.
 */

public class DashboardActivity extends AppCompatActivity {
    TextView txtDash;
    Button btnLogout,btnGridView,btnListView,btnHorizontalView,btnFrag;
    String email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnGridView = (Button) findViewById(R.id.btnGridView);
        btnListView = (Button) findViewById(R.id.btnListView);
        btnHorizontalView = (Button) findViewById(R.id.btnHorizontalView);
        btnFrag = (Button) findViewById(R.id.btnFrag);
        txtDash = (TextView) findViewById(R.id.txtDash);
        Intent intent = getIntent();
        email = intent.getStringExtra("UserEmail");
        txtDash.setText("Welcome"+" "+email);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Toast.makeText(DashboardActivity.this,"Logout Sucessfull",Toast.LENGTH_SHORT).show();
            }
        });

        btnGridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DashboardActivity.this,GridViewActivity2.class);
                startActivity(intent1);
            }
        });

        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DashboardActivity.this,ListViewActivity.class);
                startActivity(intent1);
            }
        });

        btnHorizontalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DashboardActivity.this,HorizontalViewActivity2.class);
                startActivity(intent1);
            }
        });

        btnFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DashboardActivity.this,MyFragmentActivity.class);
                startActivity(intent1);
            }
        });
    }
}
