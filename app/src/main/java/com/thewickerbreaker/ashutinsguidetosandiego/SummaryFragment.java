package com.thewickerbreaker.ashutinsguidetosandiego;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    TextView peopleText;
    ListView mainListView;
    TextView available;
    TextView leaveMeAlone;
    Switch availabilitySwitch;

    public SummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.summary_list, container, false);
        available = (TextView) rootview.findViewById(R.id.available_text);
        leaveMeAlone = (TextView) rootview.findViewById(R.id.leave_me_text);
        availabilitySwitch = (Switch) rootview.findViewById(R.id.available_switch);
        mainListView = (ListView) rootview.findViewById(R.id.summary_list);


        availabilitySwitch.setChecked(true);
        leaveMeAlone.setVisibility(View.INVISIBLE);

        availabilitySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!availabilitySwitch.isChecked()) {
                    available.setVisibility(View.VISIBLE);
                    leaveMeAlone.setVisibility(View.INVISIBLE);
                } else {
                    available.setVisibility(View.INVISIBLE);
                    leaveMeAlone.setVisibility(View.VISIBLE);

                }
            }
        });


        return rootview;


    }


    @Override
    public void onResume() {
        super.onResume();

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

        SummaryItemAdapter mSummaryItemAdapter = new SummaryItemAdapter(getActivity(), summaryItems);

        mSummaryItemAdapter.notifyDataSetChanged();

        mainListView.setAdapter(mSummaryItemAdapter);


    }
}
