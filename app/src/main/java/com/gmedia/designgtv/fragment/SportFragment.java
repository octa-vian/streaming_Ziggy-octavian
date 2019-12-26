package com.gmedia.designgtv.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gmedia.designgtv.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SportFragment extends Fragment {


    public SportFragment() {
        // Required empty public constructor
    }
    public static SportFragment newInstance()   {
        return new SportFragment();
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sport, container, false);
    }

}
