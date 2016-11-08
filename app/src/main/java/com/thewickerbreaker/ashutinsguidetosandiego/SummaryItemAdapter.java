package com.thewickerbreaker.ashutinsguidetosandiego;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bunker on 10/26/2016.
 */

public class SummaryItemAdapter extends ArrayAdapter<SummaryItems> {

    private static final String LOG_TAG = ItemAdapter.class.getSimpleName();

    public SummaryItemAdapter(Activity context, ArrayList<SummaryItems> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        SummaryItems currentItem = getItem(position);


        TextView selectedTextView = (TextView) listItemView.findViewById(R.id.selected_text);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        selectedTextView.setText(currentItem.getmSelectedText());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView choiceTextView = (TextView) listItemView.findViewById(R.id.choice_header);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        choiceTextView.setText(currentItem.getmChoiceHeader());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_image);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        if (currentItem.hasImage()) {
            iconView.setImageResource(currentItem.getmListImage());
        } else {
            iconView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.main_layout);

        textContainer.setBackgroundResource(currentItem.getmSumContainerColor());

        View imageContainer = listItemView.findViewById(R.id.list_image);

        imageContainer.setBackgroundResource(currentItem.getmSumImageColor());

        if (currentItem.getmSumContainerColor() == currentItem.getmSumContainerColor()) {

            choiceTextView.setTextColor(ContextCompat.getColor(getContext(),
                    currentItem.getmSumChoiceColor()));

            selectedTextView.setTextColor(ContextCompat.getColor(getContext(),
                    currentItem.getmSumSelectedColor()));

        }



        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;

    }

}
