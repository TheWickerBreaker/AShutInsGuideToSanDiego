package com.thewickerbreaker.ashutinsguidetosandiego;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment {

    ImageView mainImage;
    String activityName = "";
    String selectedText;
    int activityImageId;
    int imageColor = R.color.colorPrimary;
    int containerColor = R.color.padres_orange;
    int choiceTextColor = R.color.padres_yellow;
    int selectedTextColor = R.color.padres_light;

    OnActivitySelectedListener mCallback;
    private ArrayList<SummaryItems> activityArray = new ArrayList<SummaryItems>();

    public ActivitiesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (ActivitiesFragment.OnActivitySelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.choice_list, container, false);

        mainImage = (ImageView) rootview.findViewById(R.id.item_main_image);

        mainImage.setImageResource(R.drawable.stayclassy);
        final ArrayList<Items> items = new ArrayList<Items>();
        items.add(new Items("Writing", R.drawable.writing_monkey));
        items.add(new Items("Working", R.drawable.working_monkey));
        items.add(new Items("Watching TV", R.drawable.monkey_watching_tv));
        items.add(new Items("Drinking", R.drawable.drinking_monkey));
        items.add(new Items("Swimming", R.drawable.swimming_monkey));
        items.add(new Items("Gardening", R.drawable.gardening_monkey));
        items.add(new Items("Napping", R.drawable.napping_monkey));

        for (Items item : items ) {
            item.setmSelectedText("");
        }

        final ItemAdapter mItemAdapter = new ItemAdapter(getActivity(), items, containerColor,
                imageColor, selectedTextColor, choiceTextColor);

        ListView mainListView = (ListView) rootview.findViewById(R.id.list);

        mainListView.setAdapter(mItemAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                activityImageId = items.get(position).getmListImage();

                activityName = items.get(position).getmChoiceHeader();

                mainImage.setImageResource(items.get(position).getmListImage());

                if (items.get(position).getmSelectedText() == "") {

                    items.get(position).setmSelectedText("I'm...");

                    selectedText = items.get(position).getmSelectedText();

                    if (!activityArray.isEmpty()) {
                        boolean added = false;

                        for (int i = 0; i < activityArray.size(); ++i) {


                            if (!activityArray.get(i).getmChoiceHeader().equals(activityName) && !added) {


                                activityArray.add(new SummaryItems(activityName, activityImageId, imageColor,
                                        containerColor, choiceTextColor, selectedText, selectedTextColor));

                                added = true;
                            }

                        }

                    } else {
                        activityArray.add(new SummaryItems(activityName, activityImageId, imageColor,
                                containerColor, choiceTextColor, selectedText, selectedTextColor));
                    }

                } else {
                    items.get(position).setmSelectedText("");

                    selectedText = items.get(position).getmSelectedText();

                    if (!activityArray.isEmpty()) {
                        for (int i = 0; i < activityArray.size(); ++i) {
                            if (activityArray.get(i).getmChoiceHeader().equals(activityName)) {

                                activityArray.remove(i);


                            }
                        }
                    }



                }

                mCallback.onActivitySelected(activityArray);

                mItemAdapter.notifyDataSetChanged();
            }
        });

        return rootview;
    }

    // Container Activity must implement this interface
    public interface OnActivitySelectedListener {
        public void onActivitySelected(ArrayList<SummaryItems> activityArray);
    }
}
