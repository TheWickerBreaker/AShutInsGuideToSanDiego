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

import static com.thewickerbreaker.ashutinsguidetosandiego.R.id.list;

/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {

    private ImageView mainImage;
    private String personsName = "";
    private String selectedText;
    private int personImageId;
    private int imageColor = R.color.padres_light;
    private int containerColor = R.color.padres_yellow;
    private int choiceTextColor = R.color.colorPrimary;
    private int selectedTextColor = R.color.padres_orange;
    private OnPersonSelectedListener mCallback;
    private ArrayList<SummaryItems> peopleArray = new ArrayList<>();

    public PeopleFragment() {
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
            mCallback = (OnPersonSelectedListener) activity;
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
        mainImage.setImageResource(R.drawable.sdchicken);

        /**
         * Set the ArrayList of People options to choose from.
         */
        final ArrayList<Items> items = new ArrayList<>();
        items.add(new Items("Maureen", R.drawable.marueen));
        items.add(new Items("Rachael", R.drawable.rachael));
        items.add(new Items("Grayson", R.drawable.grayson));
        items.add(new Items("Zion", R.drawable.zion));
        items.add(new Items("Dennis", R.drawable.dennis));
        items.add(new Items("Jordan", R.drawable.jordan));
        items.add(new Items("Nobody", R.drawable.nobody));

        /**
         * For what ever reason, I need this to make it so that the user doesn't have to click twice
         * in order to note their selected person.
         */
        for (Items item : items) {
            item.setmSelectedText("");
        }

        /**
         * Sends data to the to the ItemAdapter to populate the ListView within the
         * PeopleFragment.
         */
        final ItemAdapter mItemAdapter = new ItemAdapter(getActivity(), items, containerColor,
                imageColor, selectedTextColor, choiceTextColor);
        ListView mainListView = (ListView) rootview.findViewById(list);
        mainListView.setAdapter(mItemAdapter);

        /**
         * When list items are check, this changes the notation as to whether or not the item was
         * selected as well as sends selected items to the SummaryFragment to be displayed there.
         */
        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                personImageId = items.get(position).getmListImage();
                personsName = items.get(position).getmChoiceHeader();

                /**
                 * Changes the main image to be the image of the most recently selected item.
                 */
                mainImage.setImageResource(items.get(position).getmListImage());

                /**
                 * Changes notation of selected items.
                 */
                if (items.get(position).getmSelectedText().equals("")) {

                    items.get(position).setmSelectedText("I'm with...");
                    selectedText = items.get(position).getmSelectedText();

                    if (!peopleArray.isEmpty()) {
                        boolean added = false;

                        /**
                         * If "Nobody" is selected this clears any other selected person to make
                         * "Nobody" an accurate term.
                         */
                        if (personsName.equals("Nobody")) {
                            peopleArray.clear();
                            for (Items item : items) {
                                item.setmSelectedText("");
                            }
                            peopleArray.add(new SummaryItems(personsName, personImageId, imageColor,
                                    containerColor, choiceTextColor, selectedText, selectedTextColor));
                            items.get(position).setmSelectedText("I'm with...");

                        } else {

                            /**
                             * If "Nobody" is selected first this will clear "Nobody" when an actual
                             * person is selected.
                             */
                            for (int i = 0; i < peopleArray.size(); ++i) {

                                if (peopleArray.get(i).getmChoiceHeader().equals("Nobody")) {
                                    peopleArray.clear();
                                    for (Items item : items) {
                                        item.setmSelectedText("");
                                    }
                                    peopleArray.add(new SummaryItems(personsName, personImageId, imageColor,
                                            containerColor, choiceTextColor, selectedText, selectedTextColor));
                                    items.get(position).setmSelectedText("I'm with...");
                                }
                            }
                        }


                        /**
                         * Checks for duplicates before adding them to the Array to be sent to the
                         * SummaryFragment to be displayed.
                         */
                        for (int i = 0; i < peopleArray.size(); ++i) {

                            if (!peopleArray.get(i).getmChoiceHeader().equals(personsName) && !added) {

                                peopleArray.add(new SummaryItems(personsName, personImageId, imageColor,
                                        containerColor, choiceTextColor, selectedText, selectedTextColor));

                                added = true;
                            }
                        }

                    } else {
                        peopleArray.add(new SummaryItems(personsName, personImageId, imageColor,
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

                    if (!peopleArray.isEmpty()) {
                        for (int i = 0; i < peopleArray.size(); ++i) {
                            if (peopleArray.get(i).getmChoiceHeader().equals(personsName)) {
                                peopleArray.remove(i);
                            }
                        }
                    }
                }
                mCallback.onPersonSelected(peopleArray);
                mItemAdapter.notifyDataSetChanged();
            }
        });
        return rootview;
    }

    /**
     * Sends arraylist to MainActivity to then be sent to the SummaryFragment to display selected
     * items.
     */
    public interface OnPersonSelectedListener {
        void onPersonSelected(ArrayList<SummaryItems> peopleArray);
    }
}