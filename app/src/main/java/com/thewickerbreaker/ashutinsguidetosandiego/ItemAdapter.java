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

class ItemAdapter extends ArrayAdapter<Items> {

    private int mTextContainerColorId;
    private int mImageColorId;
    private int mSelectedColor;
    private int mMainTextColor;

    /**
     * Sorts data with information to populate into the selectable items listed throughout the app.
     */
    ItemAdapter(Activity context, ArrayList<Items> items, int textContainerColorId,
                int imageColorId, int selectedColor, int mainTextColor) {
        super(context, 0, items);
        mTextContainerColorId = textContainerColorId;
        mImageColorId = imageColorId;
        mSelectedColor = selectedColor;
        mMainTextColor = mainTextColor;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_items, parent, false);
        }

        Items currentItem = getItem(position);

        TextView selectedTextView = (TextView) listItemView.findViewById(R.id.selected_text);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        assert currentItem != null;
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

        int textContainerColor = ContextCompat.getColor(getContext(), mTextContainerColorId);
        int imageColor = ContextCompat.getColor(getContext(), mImageColorId);
        int selectedColor = ContextCompat.getColor(getContext(), mSelectedColor);
        int mainText = ContextCompat.getColor(getContext(), mMainTextColor);

        textContainer.setBackgroundColor(textContainerColor);
        iconView.setBackgroundColor(imageColor);
        selectedTextView.setTextColor(selectedColor);
        choiceTextView.setTextColor(mainText);

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}