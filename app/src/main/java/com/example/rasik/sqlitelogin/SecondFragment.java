package com.example.rasik.sqlitelogin;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by rasik on 20/9/17.
 */

public class SecondFragment extends Fragment {
    Button btnFrag2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.actvity_fragment2,container,false);
        btnFrag2 = view.findViewById(R.id.btnFrag2);

        return view;

    }
}
