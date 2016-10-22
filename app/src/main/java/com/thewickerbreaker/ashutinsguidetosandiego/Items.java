package com.thewickerbreaker.ashutinsguidetosandiego;

import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Bunker on 10/19/2016.
 */

public class Items {

    private String mChoiceHeader;
    private String mSelectedText;
    private int mListImage = NO_IMAGE_PROVIDED;


    private static final int NO_IMAGE_PROVIDED = -1;

    /*public Items(String choiceHeader, String selectedText, int selectedTextColor){
        mChoiceHeader = choiceHeader;
        mSelectedText = selectedText;
        mSelectedTextColor = selectedTextColor;
    }*/

    public Items(String choiceHeader, String selectedText, int imageResourceID){
        mChoiceHeader = choiceHeader;
        mSelectedText = selectedText;
        mListImage = imageResourceID;
    }

    public String getmChoiceHeader() {
        return mChoiceHeader;
    }

    public String getmSelectedText() {
        return mSelectedText;
    }

    public int getmListImage(){
        return mListImage;
    }





    public boolean hasImage(){
        return  mListImage != NO_IMAGE_PROVIDED;
    }


}
