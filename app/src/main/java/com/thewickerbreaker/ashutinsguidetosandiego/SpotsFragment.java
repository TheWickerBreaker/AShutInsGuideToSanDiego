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

    String spotName = "";
    int spotImageId;
    int imageColor = R.color.padres_yellow;
    int containerColor = R.color.colorPrimary;
    int choiceTextColor = R.color.padres_orange;
    OnSpotSelectedListener mCallback;
    private ArrayList<SummaryItems> spotArray = new ArrayList<SummaryItems>();

    public SpotsFragment() {
        // Required empty public constructor
    }

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

        final ArrayList<Items> items = new ArrayList<Items>();
        items.add(new Items("My Home Office", R.drawable.padre));
        items.add(new Items("My Living Room", R.drawable.squareplaceholder));
        items.add(new Items("My Bed", R.drawable.squareplaceholder));
        items.add(new Items("The Garden", R.drawable.squareplaceholder));
        items.add(new Items("The Pool", R.drawable.squareplaceholder));
        items.add(new Items("Main Living Room", R.drawable.squareplaceholder));
        items.add(new Items("Somewhere Else", R.drawable.squareplaceholder));

        final ItemAdapter mItemAdapter = new ItemAdapter(getActivity(), items, containerColor,
                imageColor, R.color.padres_light, choiceTextColor);

        ListView mainListView = (ListView) rootview.findViewById(R.id.list);

        mainListView.setAdapter(mItemAdapter);

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView mainImage = (ImageView)rootview.findViewById(R.id.item_main_image);

                spotImageId = items.get(position).getmListImage();

                spotName = items.get(position).getmChoiceHeader();

                for (Items item : items ) {
                    item.setmSelectedText("");
                }

                mainImage.setImageResource(items.get(position).getmListImage());

                items.get(position).setmSelectedText("I am\nHere!");

                if (!spotArray.isEmpty()) {
                    spotArray.clear();
                }

                spotArray.add(new SummaryItems(spotName, spotImageId, imageColor,
                        containerColor, choiceTextColor));

                mCallback.onSpotSelected(spotArray);

                mItemAdapter.notifyDataSetChanged();

            }
        });

        return rootview;
    }

    // Container Activity must implement this interface
    public interface OnSpotSelectedListener {
        public void onSpotSelected(ArrayList<SummaryItems> spotArray);
    }


}
