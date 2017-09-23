package com.example.rasik.sqlitelogin;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by rasik on 20/9/17.
 */

public class ThirdFragment extends Fragment implements View.OnClickListener{
    Button mSendData;
    DataSenderInterFace send;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_fragment3,container,false);
    }

    /*
        make sure you initialize your ui component in onActivityCreated
        not in onCreate , because  this method is call with activity onCreate method
        that will throw error
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        send = (DataSenderInterFace) getActivity();
        mSendData = (Button) getActivity().findViewById(R.id.send_data);
        mSendData.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        send.sendData("Text Updated Sucessfully");

    }
}