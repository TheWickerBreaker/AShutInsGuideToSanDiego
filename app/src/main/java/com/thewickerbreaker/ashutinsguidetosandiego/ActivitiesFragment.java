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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment {

    private ImageView mainImage;
    private String activityName = "";
    private String selectedText;
    private int activityImageId;
    private int imageColor = R.color.colorPrimary;
    private int containerColor = R.color.padres_orange;
    private int choiceTextColor = R.color.padres_yellow;
    private int selectedTextColor = R.color.padres_light;
    private OnActivitySelectedListener mCallback;
    private ArrayList<SummaryItems> activityArray = new ArrayList<>();

    public ActivitiesFragment() {
        // Required empty public constructor
    }

    /**
     * Attaches items to send to the MainActivity to then be sent to the SummaryFragment
     * to display selected items.
     */
    @SuppressWarnings("deprecation")
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

        /**
         * Set default main image.
         */
        mainImage = (ImageView) rootview.findViewById(R.id.item_main_image);
        mainImage.setImageResource(R.drawable.stayclassy);

        /**
         * Set the ArrayList of Activity options to choose from.
         */
        final ArrayList<Items> items = new ArrayList<>();
        items.add(new Items(getString(R.string.writing), R.drawable.writing_monkey));
        items.add(new Items(getString(R.string.working), R.drawable.working_monkey));
        items.add(new Items(getString(R.string.watching_tv), R.drawable.monkey_watching_tv));
        items.add(new Items(getString(R.string.drinking), R.drawable.drinking_monkey));
        items.add(new Items(getString(R.string.swimming), R.drawable.swimming_monkey));
        items.add(new Items(getString(R.string.gardening), R.drawable.gardening_monkey));
        items.add(new Items(getString(R.string.napping), R.drawable.napping_monkey));

        /**
         * For whatever reason, I need this to make it so that the user doesn't have to click twice
         * in order to note their selected activity.
         */
        for (Items item : items) {
            item.setmSelectedText("");
        }

        /**
         * Sends data to the to the ItemAdapter to populate the ListView within the
         * ActivitiesFragment.
         */
        final ItemAdapter mItemAdapter = new ItemAdapter(getActivity(), items, containerColor,
                imageColor, selectedTextColor, choiceTextColor);
        ListView mainListView = (ListView) rootview.findViewById(R.id.list);
        mainListView.setAdapter(mItemAdapter);

        /**
         * When list items are check, this changes the notation as to whether or not the item was
         * selected as well as sends selected items to the SummaryFragment to be displayed there.
         */
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                activityImageId = items.get(position).getmListImage();
                activityName = items.get(position).getmChoiceHeader();

                /**
                 * Changes the main image to be the image of the most recently selected item.
                 */
                mainImage.setImageResource(items.get(position).getmListImage());

                /**
                 * Changes notation of selected items.
                 */
                if (items.get(position).getmSelectedText().equals("")) {

                    items.get(position).setmSelectedText("I am...");
                    selectedText = items.get(position).getmSelectedText();

                    /**
                     * Checks for duplicates before adding them to the Array to be sent to the
                     * SummaryFragment to be displayed.
                     */
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
                    /**
                     * Clears selected item notation when an item is deselected as well as removes
                     * said item from the array being sent to the SummaryFragment to not display
                     * items that are not selected.
                     */
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

    /**
     * Sends arraylist to MainActivity to then be sent to the SummaryFragment to display selected
     * items.
     */
    public interface OnActivitySelectedListener {
        void onActivitySelected(ArrayList<SummaryItems> activityArray);
    }
}