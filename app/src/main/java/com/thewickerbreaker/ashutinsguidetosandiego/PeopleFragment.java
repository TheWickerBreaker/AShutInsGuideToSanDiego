package com.thewickerbreaker.ashutinsguidetosandiego;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

import static android.media.CamcorderProfile.get;
import static android.provider.Telephony.Mms.Part.TEXT;
import static android.view.View.Y;
import static com.thewickerbreaker.ashutinsguidetosandiego.R.id.list;
import static com.thewickerbreaker.ashutinsguidetosandiego.R.id.list_image;
import static java.util.Arrays.asList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {

    ImageView mainImage;
    String personsName = "";
    String selectedText;
    int personImageId;
    int imageColor = R.color.padres_light;
    int containerColor = R.color.padres_yellow;
    int choiceTextColor = R.color.colorPrimary;
    int selectedTextColor = R.color.padres_orange;
    OnPersonSelectedListener mCallback;
    private ArrayList<SummaryItems> peopleArray = new ArrayList<SummaryItems>();


    public PeopleFragment() {
        // Required empty public constructor
    }

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

        mainImage = (ImageView) rootview.findViewById(R.id.item_main_image);

        mainImage.setImageResource(R.drawable.sdchicken);

        final ArrayList<Items> items = new ArrayList<Items>();
        items.add(new Items("Maureen", R.drawable.marueen));
        items.add(new Items("Rachael", R.drawable.rachael));
        items.add(new Items("Grayson", R.drawable.grayson));
        items.add(new Items("Zion", R.drawable.zion));
        items.add(new Items("Dennis", R.drawable.dennis));
        items.add(new Items("Jordan", R.drawable.jordan));
        items.add(new Items("Nobody", R.drawable.nobody));

        for (Items item : items) {
            item.setmSelectedText("");
        }

        final ItemAdapter mItemAdapter = new ItemAdapter(getActivity(), items, containerColor,
                imageColor, selectedTextColor, choiceTextColor);

        ListView mainListView = (ListView) rootview.findViewById(list);

        mainListView.setAdapter(mItemAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                personImageId = items.get(position).getmListImage();

                personsName = items.get(position).getmChoiceHeader();

                if (items.get(position).getmSelectedText() == "") {


                    items.get(position).setmSelectedText("I'm with...");

                    selectedText = items.get(position).getmSelectedText();


                    if (!peopleArray.isEmpty()) {
                        boolean added = false;

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

                mainImage.setImageResource(items.get(position).getmListImage());

                mItemAdapter.notifyDataSetChanged();

            }
        });


        return rootview;
    }


    // Container Activity must implement this interface
    public interface OnPersonSelectedListener {
        public void onPersonSelected(ArrayList<SummaryItems> peopleArray);
    }
}
