package com.thewickerbreaker.ashutinsguidetosandiego;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
        items.add(new Items("My Home Office", "I am\nHere!", R.drawable.padre));
        items.add(new Items("My Living Room", "I am\nHere!", R.drawable.squareplaceholder));
        items.add(new Items("My Bed", "I am\nHere!", R.drawable.squareplaceholder));
        items.add(new Items("The Garden", "I am\nHere!", R.drawable.squareplaceholder));
        items.add(new Items("The Pool", "I am\nHere!", R.drawable.squareplaceholder));
        items.add(new Items("Main Living Room", "I am\nHere!", R.drawable.squareplaceholder));



        final ItemAdapter itemAdapter = new ItemAdapter(getActivity(), items, R.color.colorPrimary, R.color.padres_light, R.color.padres_yellow);

        final ListView listView = (ListView) rootview.findViewById(R.id.list);



        listView.setAdapter(itemAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                TextView selected = (TextView) view.findViewById(R.id.selected_text);

                TextView all = (TextView) parent.findViewById(R.id.selected_text);

                //for (int i = items.size() - 1; i >= 0; i--) {

                    for (int position1 = position; position1 > 0; position1--) {
                        if (all.getVisibility() == View.VISIBLE) {

                            all.setVisibility(View.INVISIBLE);
                        }
                    }




                //}




                    if (selected.getVisibility() == View.VISIBLE) {

                        selected.setVisibility(View.INVISIBLE);
                    } else {
                        selected.setVisibility(View.VISIBLE);
                    }


            }
        });




        return rootview;
    }



}
