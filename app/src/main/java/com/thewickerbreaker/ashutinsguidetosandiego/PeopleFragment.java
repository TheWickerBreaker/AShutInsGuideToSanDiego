package com.thewickerbreaker.ashutinsguidetosandiego;



import android.app.Activity;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
public class PeopleFragment extends Fragment {

    String personsName = "";

    OnPersonSelectedListener mCallback;

    // Container Activity must implement this interface
    public interface OnPersonSelectedListener {
        public void onPersonSelected(String person);
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





    private String mPeopleText;

    public PeopleFragment() {
        // Required empty public constructor
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


        for (Items item : items ) {
                    item.setmSelectedText("");
                }

        final ItemAdapter mItemAdapter = new ItemAdapter(getActivity(), items, R.color.padres_yellow,
                R.color.padres_light, R.color.padres_orange, R.color.colorPrimary);

        ListView mainListView = (ListView) rootview.findViewById(R.id.list);



        mainListView.setAdapter(mItemAdapter);



        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView mainImage = (ImageView)rootview.findViewById(R.id.item_main_image);



               if (items.get(position).getmSelectedText() == "") {

                    items.get(position).setmSelectedText("I am\nwith!");

                } else {
                    items.get(position).setmSelectedText("");

                }

                mainImage.setImageResource(items.get(position).getmListImage());

                personsName = items.get(position).getmChoiceHeader();


                mCallback.onPersonSelected(personsName);

                mItemAdapter.notifyDataSetChanged();






            }
        });



        return rootview;


    }


}
