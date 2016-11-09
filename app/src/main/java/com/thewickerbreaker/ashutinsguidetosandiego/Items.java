package com.thewickerbreaker.ashutinsguidetosandiego;

class Items {

    private static final int NO_IMAGE_PROVIDED = -1;
    private String mChoiceHeader;
    private String mSelectedText;
    private int mListImage = NO_IMAGE_PROVIDED;

    Items(String choiceHeader, int imageResourceID) {
        mChoiceHeader = choiceHeader;
        mListImage = imageResourceID;
    }

    String getmChoiceHeader() {
        return mChoiceHeader;
    }

    String getmSelectedText() {
        return mSelectedText;
    }

    void setmSelectedText(String mSelectedText) {
        this.mSelectedText = mSelectedText;
    }

    int getmListImage() {
        return mListImage;
    }

    boolean hasImage() {
        return mListImage != NO_IMAGE_PROVIDED;
    }
}
