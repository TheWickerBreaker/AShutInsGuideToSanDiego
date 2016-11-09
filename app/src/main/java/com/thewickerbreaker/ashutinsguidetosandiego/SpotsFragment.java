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
public class SpotsFragment extends Fragment {

    private ImageView mainImage;
    private String spotName = "";
    private String selectedText;
    private int spotImageId;
    private int imageColor = R.color.padres_yellow;
    private int containerColor = R.color.colorPrimary;
    private int choiceTextColor = R.color.padres_light;
    private int selectedTextColor = R.color.padres_orange;
    private OnSpotSelectedListener mCallback;
    private ArrayList<SummaryItems> spotArray = new ArrayList<>();

    public SpotsFragment() {
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
            mCallback = (SpotsFragment.OnSpotSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.choice_list, container, false);

        /**
         * Set default main image.
         */
        mainImage = (ImageView) rootview.findViewById(R.id.item_main_image);
        mainImage.setImageResource(R.drawable.robertos_taco_shop);

        /**
         * Set the ArrayList of Activity options to choose from.
         */
        final ArrayList<Items> items = new ArrayList<>();
        items.add(new Items("My Home Office", R.drawable.office));
        items.add(new Items("My Living Room", R.drawable.living_room));
        items.add(new Items("My Bed", R.drawable.bed));
        items.add(new Items("The Garden", R.drawable.garden));
        items.add(new Items("The Pool", R.drawable.pool));
        items.add(new Items("The Main Living Room", R.drawable.main_living_room));
        items.add(new Items("Somewhere Else", R.drawable.somewhere));

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

                spotImageId = items.get(position).getmListImage();
                spotName = items.get(position).getmChoiceHeader();

                /**
                 * Since only one item is allowed to be selected at a time. This resets
                 * the selected notation when a new item is chosen.
                 */
                for (Items item : items) {
                    item.setmSelectedText("");
                }

                /**
                 * Changes the main image to be the image of the most recently selected item.
                 */
                mainImage.setImageResource(items.get(position).getmListImage());

                /**
                 * Changes notation of selected items.
                 */
                items.get(position).setmSelectedText("I'm at...");

                /**
                 * Clears spotArray each time a new item is selected.
                 */
                if (!spotArray.isEmpty()) {
                    spotArray.clear();
                }

                selectedText = items.get(position).getmSelectedText();
                spotArray.add(new SummaryItems(spotName, spotImageId, imageColor,
                        containerColor, choiceTextColor, selectedText, selectedTextColor));

                mCallback.onSpotSelected(spotArray);
                mItemAdapter.notifyDataSetChanged();
            }
        });
        return rootview;
    }

    /**
     * Sends arraylist to MainActivity to then be sent to the SummaryFragment to display selected
     * items.
     */
    public interface OnSpotSelectedListener {
        void onSpotSelected(ArrayList<SummaryItems> spotArray);
    }


}
