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

    String personsName = "";
    int personImageId;
    int imageColor = R.color.padres_light;
    int containerColor = R.color.padres_yellow;
    int choiceTextColor = R.color.colorPrimary;
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
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.choice_list, container, false);

        final ArrayList<Items> items = new ArrayList<Items>();
        items.add(new Items("Maureen", R.drawable.padre));
        items.add(new Items("Rachael", R.drawable.squareplaceholder));
        items.add(new Items("Grayson", R.drawable.squareplaceholder));
        items.add(new Items("Zion", R.drawable.squareplaceholder));
        items.add(new Items("Dennis", R.drawable.squareplaceholder));
        items.add(new Items("Jordan", R.drawable.squareplaceholder));
        items.add(new Items("Nobody", R.drawable.squareplaceholder));

        /*for (Items item : items) {
            item.setmSelectedText("");
        }*/

        final ItemAdapter mItemAdapter = new ItemAdapter(getActivity(), items, containerColor,
                imageColor, R.color.padres_orange, choiceTextColor);

        ListView mainListView = (ListView) rootview.findViewById(list);

        mainListView.setAdapter(mItemAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView mainImage = (ImageView) rootview.findViewById(R.id.item_main_image);

                personImageId = items.get(position).getmListImage();

                personsName = items.get(position).getmChoiceHeader();

                if (items.get(position).getmSelectedText() == "") {

                    items.get(position).setmSelectedText("I am\nwith!");

                    peopleArray.add(new SummaryItems(personsName, personImageId, imageColor,
                            containerColor, choiceTextColor));

                } else {

                    peopleArray.remove(new SummaryItems(personsName, personImageId, imageColor,
                            containerColor, choiceTextColor));

                    items.get(position).setmSelectedText("");
                }

                mainImage.setImageResource(items.get(position).getmListImage());

                mCallback.onPersonSelected(peopleArray);
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
