package com.example.andriginting.projectlocation.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.andriginting.projectlocation.R;

/**
 * Created by Andri Ginting on 2/12/2017.
 */

public class profileFragment extends Fragment {
    public profileFragment() {
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_profil,container,false);
        return view;
    }
}
