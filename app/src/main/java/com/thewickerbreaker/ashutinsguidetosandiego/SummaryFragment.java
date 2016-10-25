package com.thewickerbreaker.ashutinsguidetosandiego;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.attr.defaultValue;
import static android.R.attr.key;
import static android.R.attr.name;
import static android.os.Build.VERSION_CODES.M;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    TextView peopleText;



    public SummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.summary_list, container, false);
        peopleText = (TextView) rootview.findViewById(R.id.textView26);

        Intent intent = getActivity().getIntent();
        if (intent.getExtras() != null) {
            String name =intent.getStringExtra("name");

            Log.i("Name", name);
        }

        return rootview;

    }



}
