package com.example.rasik.sqlitelogin;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import static com.example.rasik.sqlitelogin.R.id.fragment2;


/**
 * Created by rasik on 20/9/17.
 */

public class MyFragmentActivity  extends AppCompatActivity implements DataSenderInterFace {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_activity);

    }



    @Override
    public void sendData(String data) {

        FragmentManager manager = getFragmentManager();
        FirstFragment fragment1= (FirstFragment) manager.findFragmentById(R.id.fragment);
        fragment1.updateMe("updated data sent");


    }
}