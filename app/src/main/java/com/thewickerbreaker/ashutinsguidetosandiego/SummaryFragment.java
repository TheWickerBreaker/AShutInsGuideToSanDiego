package com.thewickerbreaker.ashutinsguidetosandiego;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    TextView peopleText;
    ListView mainListView;

    public SummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.choice_list, container, false);

        peopleText = (TextView) rootview.findViewById(R.id.textView26);
        mainListView = (ListView) rootview.findViewById(R.id.list);
        return rootview;
    }

    @Override
    public void onResume() {

        ArrayList<SummaryItems> spotArray = (((MainActivity) getActivity()).getmSpotArray());
        ArrayList<SummaryItems> activityArray = (((MainActivity) getActivity()).getmActivityArray());
        ArrayList<SummaryItems> peopleArray = (((MainActivity) getActivity()).getmPeopleArray());

        ArrayList<SummaryItems> summaryItems = new ArrayList<SummaryItems>();

        if (spotArray != null) {
            summaryItems.addAll(spotArray);
        }

        if (activityArray != null) {
            summaryItems.addAll(activityArray);
        }

        if (peopleArray != null) {
            summaryItems.addAll(peopleArray);
        }

        SummaryItemAdapter mSummaryItemAdapter = new SummaryItemAdapter(getActivity(), summaryItems,
                R.color.padres_orange);

        mainListView.setAdapter(mSummaryItemAdapter);
        super.onResume();
    }
}
