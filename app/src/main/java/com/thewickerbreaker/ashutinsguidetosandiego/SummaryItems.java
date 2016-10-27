package com.thewickerbreaker.ashutinsguidetosandiego;

/**
 * Created by Bunker on 10/26/2016.
 */

class SummaryItems {

    private static final int NO_IMAGE_PROVIDED = -1;
    private String mChoiceHeader;
    private String mSelectedText;
    private int mSumImageColor;
    private int mSumContainerColor;
    private int mSumChoiceColor;
    private int mListImage = NO_IMAGE_PROVIDED;

    SummaryItems(String choiceHeader, int imageResourceID, int imageColor, int containerColor,
                 int choiceColor) {
        mChoiceHeader = choiceHeader;
        mListImage = imageResourceID;
        mSumImageColor = imageColor;
        mSumContainerColor = containerColor;
        mSumChoiceColor = choiceColor;
    }

    String getmChoiceHeader() {
        return mChoiceHeader;
    }

    String getmSelectedText() {
        return mSelectedText;
    }

    int getmListImage() {
        return mListImage;
    }

    boolean hasImage() {
        return mListImage != NO_IMAGE_PROVIDED;
    }

    int getmSumImageColor() {
        return mSumImageColor;
    }

    int getmSumContainerColor() {
        return mSumContainerColor;
    }

    public int getmSumChoiceColor() {
        return mSumChoiceColor;
    }
}
