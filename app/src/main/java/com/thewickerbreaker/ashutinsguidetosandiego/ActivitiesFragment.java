package com.thewickerbreaker.ashutinsguidetosandiego;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivitiesFragment extends Fragment {


    public ActivitiesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.choice_list, container, false);


        final ArrayList<Items> items = new ArrayList<Items>();
        items.add(new Items("Writing", R.drawable.padre));
        items.add(new Items("Working", R.drawable.squareplaceholder));
        items.add(new Items("Watching TV Shows", R.drawable.squareplaceholder));
        items.add(new Items("Watching Movies", R.drawable.squareplaceholder));
        items.add(new Items("Swimming", R.drawable.squareplaceholder));
        items.add(new Items("Gardening", R.drawable.squareplaceholder));
        items.add(new Items("Napping", R.drawable.squareplaceholder));

        for (Items item : items ) {
            item.setmSelectedText("");
        }

        final ItemAdapter mItemAdapter = new ItemAdapter(getActivity(), items, R.color.padres_orange,
                R.color.colorPrimary, R.color.padres_light, R.color.padres_yellow);

        ListView mainListView = (ListView) rootview.findViewById(R.id.list);



        mainListView.setAdapter(mItemAdapter);





        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView mainImage = (ImageView)rootview.findViewById(R.id.item_main_image);




                mainImage.setImageResource(items.get(position).getmListImage());


                if (items.get(position).getmSelectedText() == "") {

                    items.get(position).setmSelectedText("I am\ndoing\nthis!");
                } else {
                    items.get(position).setmSelectedText("");

                }







                mItemAdapter.notifyDataSetChanged();



            }
        });



        return rootview;
    }

}
