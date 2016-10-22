package com.thewickerbreaker.ashutinsguidetosandiego;

import android.app.Activity;
import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Bunker on 10/19/2016.
 */

public class ItemAdapter extends ArrayAdapter<Items> {

    private static final String LOG_TAG = ItemAdapter.class.getSimpleName();
    private int mTextColorId;
    private int mImageColorId;
    private int mSelectedColor;


    public ItemAdapter(Activity context, ArrayList<Items> items, int textColorId, int imageColorId, int selectedColor) {
        super(context, 0, items);
        mTextColorId = textColorId;
        mImageColorId = imageColorId;
        mSelectedColor = selectedColor;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }


        Items currentItem = getItem(position);


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
        View imageContainer = listItemView.findViewById(R.id.list_image);


        int textColor = ContextCompat.getColor(getContext(), mTextColorId);
        int imageColor = ContextCompat.getColor(getContext(), mImageColorId);
        int selectedColor = ContextCompat.getColor(getContext(), mSelectedColor);

        textContainer.setBackgroundColor(textColor);
        imageContainer.setBackgroundColor(imageColor);
        selectedTextView.setTextColor(selectedColor);



       /* selectedTextView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                TextView selectedTextView = (TextView) v.findViewById(R.id.selected_text);


                if (selectedTextView.getVisibility() == View.VISIBLE) {

                    selectedTextView.setVisibility(View.INVISIBLE);
                } else {
                    selectedTextView.setVisibility(View.VISIBLE);
                }

            }
        });

*/

       /* RadioButton radioButton = (RadioButton)listItemView.findViewById(R.id.item_radioButton);

        radioButton.setChecked(position == selectedPosition);
        radioButton.setTag(position);

        radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedPosition = (Integer)view.getTag();
                notifyDataSetChanged();

            }


        });


*/


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;

    }


}
