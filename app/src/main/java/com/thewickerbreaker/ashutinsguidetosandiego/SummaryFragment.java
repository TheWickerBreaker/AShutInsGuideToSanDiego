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

        String selectedName = ((MainActivity) getActivity()).getmName();
        String spotName = ((MainActivity) getActivity()).getmSpot();
        String activities = ((MainActivity) getActivity()).getmActivity();

        int personImageId = ((MainActivity) getActivity()).getmPersonImageId();
        int spotImageId = (((MainActivity) getActivity()).getmSpotImageId());
        int activitiesImage = ((MainActivity) getActivity()).getmActivityImageId();

        int spotImageColor = ((MainActivity) getActivity()).getmSpotImageColor();
        int peopleImageColor = ((MainActivity) getActivity()).getmPeopleImageColor();
        int activityImageColor = ((MainActivity) getActivity()).getmActivityImageColor();

        int spotContainerColor = ((MainActivity) getActivity()).getmSpotContainerColor();
        int peopleContainerColor = ((MainActivity) getActivity()).getmPeopleContainerColor();
        int activityContainerColor = ((MainActivity) getActivity()).getmActivityContainerColor();

        int spotChoiceColor = ((MainActivity) getActivity()).getmSpotChoiceColor();
        int peopleChoiceColor = ((MainActivity) getActivity()).getmPeopleChoiceColor();
        int activityChoiceColor = ((MainActivity) getActivity()).getmActivityChoiceColor();

        ArrayList<SummaryItems> summaryItems = new ArrayList<SummaryItems>();
        summaryItems.add(new SummaryItems(spotName, spotImageId, spotImageColor, spotContainerColor,
                spotChoiceColor));
        summaryItems.add(new SummaryItems(activities, activitiesImage, activityImageColor,
                activityContainerColor, activityChoiceColor));
        summaryItems.add(new SummaryItems(selectedName, personImageId, peopleImageColor,
                peopleContainerColor, peopleChoiceColor));


        SummaryItemAdapter mSummaryItemAdapter = new SummaryItemAdapter(getActivity(), summaryItems,
                R.color.padres_orange);

        mainListView.setAdapter(mSummaryItemAdapter);
        super.onResume();
    }
}
