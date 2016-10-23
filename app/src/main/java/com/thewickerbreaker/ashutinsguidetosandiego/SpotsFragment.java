package com.thewickerbreaker.ashutinsguidetosandiego;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.CheckedInputStream;

import static android.media.CamcorderProfile.get;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpotsFragment extends Fragment {



    public SpotsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootview = inflater.inflate(R.layout.choice_list, container, false);



       // ImageView iv = new ImageView(getActivity());
        //iv.setImageResource(R.drawable.placeholder);


        final ArrayList<Items> items = new ArrayList<Items>();
        items.add(new Items("My Home Office", R.drawable.padre));
        items.add(new Items("My Living Room", R.drawable.squareplaceholder));
        items.add(new Items("My Bed", R.drawable.squareplaceholder));
        items.add(new Items("The Garden", R.drawable.squareplaceholder));
        items.add(new Items("The Pool", R.drawable.squareplaceholder));
        items.add(new Items("Main Living Room", R.drawable.squareplaceholder));
        items.add(new Items("Somewhere Else", R.drawable.squareplaceholder));



        final ItemAdapter mItemAdapter = new ItemAdapter(getActivity(), items, R.color.colorPrimary,
                R.color.padres_yellow, R.color.padres_light, R.color.padres_orange);

        ListView mainListView = (ListView) rootview.findViewById(R.id.list);



        mainListView.setAdapter(mItemAdapter);



        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ImageView mainImage = (ImageView)rootview.findViewById(R.id.item_main_image);


                for (Items item : items ) {
                    item.setmSelectedText("");
                }
                mainImage.setImageResource(items.get(position).getmListImage());


                    items.get(position).setmSelectedText("I am\nHere!");


                mItemAdapter.notifyDataSetChanged();



            }
        });




        return rootview;
    }



}
