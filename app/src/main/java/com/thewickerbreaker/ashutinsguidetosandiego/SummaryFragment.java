package com.thewickerbreaker.ashutinsguidetosandiego;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    private ListView mainListView;
    private TextView available;
    private TextView leaveMeAlone;
    private Switch availabilitySwitch;
    private ArrayList<SummaryItems> summaryItems;
    private LinearLayout adSpace;

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
        adSpace = (LinearLayout) rootview.findViewById(R.id.adSpace);

        /**
         * Sets default placeholder ad image and text.
         */
        mainListView.setVisibility(View.GONE);
        adSpace.setVisibility(View.VISIBLE);

        /**
         * Sets default position of the availability stitch.
         */
        availabilitySwitch.setOnCheckedChangeListener(null);
        availabilitySwitch.setChecked(false);
        leaveMeAlone.setVisibility(View.INVISIBLE);

        /**
         * Sets Rules for availability switch.
         */
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

        updateSummary();
        return rootview;
    }


    /**
     * Updates summaryItems from throughout the app when they are selected.
     */
    public void updateSummary() {
        ArrayList<SummaryItems> spotArray = (((MainActivity) getActivity()).getmSpotArray());
        ArrayList<SummaryItems> activityArray = (((MainActivity) getActivity()).getmActivityArray());
        ArrayList<SummaryItems> peopleArray = (((MainActivity) getActivity()).getmPeopleArray());

        mainListView.setVisibility(View.GONE);
        adSpace.setVisibility(View.VISIBLE);

        summaryItems = new ArrayList<>();

        /**
         * Adds Selected spotItems and hides the placeholder ad and text.
         */
        if (spotArray != null) {
            summaryItems.addAll(spotArray);
            mainListView.setVisibility(View.VISIBLE);
            adSpace.setVisibility(View.GONE);
        }

        /**
         * Adds Selected activityItems and hides the placeholder ad and text.
         */
        if (activityArray != null) {
            summaryItems.addAll(activityArray);
            mainListView.setVisibility(View.VISIBLE);
            adSpace.setVisibility(View.GONE);
        }

        /**
         * Adds Selected peopleItems and hides the placeholder ad and text.
         */
        if (peopleArray != null) {
            summaryItems.addAll(peopleArray);
            mainListView.setVisibility(View.VISIBLE);
            adSpace.setVisibility(View.GONE);
        }

        SummaryItemAdapter mSummaryItemAdapter = new SummaryItemAdapter(getActivity(), summaryItems);
        mainListView.setAdapter(mSummaryItemAdapter);
    }

    /**
     * Refreshes summaryItems when user returns to the summary fragment.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (summaryItems != null) {
                updateSummary();
            }
        }
    }
}
