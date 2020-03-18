package com.omaressam.hangin;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.omaressam.hangin.R;

import java.util.ArrayList;
import java.util.List;


public class fragment_nearby extends Fragment {

    public fragment_nearby() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         inflater.inflate(R.layout.fragment_nearby, container, false);


        return null;
    }

}
