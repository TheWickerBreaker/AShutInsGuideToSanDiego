package com.thewickerbreaker.ashutinsguidetosandiego;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity
        implements PeopleFragment.OnPersonSelectedListener,
        SpotsFragment.OnSpotSelectedListener, ActivitiesFragment.OnActivitySelectedListener {


    private String mName;
    private String mSpot;
    private String mActivity;

    private int mPersonImageId;
    private int mSpotImageId;
    private int mActivityImageId;

    private int mSpotImageColor;
    private int mPeopleImageColor;
    private int mActivityImageColor;

    private int mSpotContainerColor;
    private int mPeopleContainerColor;
    private int mActivityContainerColor;

    private int mSpotChoiceColor;
    private int mPeopleChoiceColor;
    private int mActivityChoiceColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        PagerAdapter adapter = new PagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Find the tab layout that shows the tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);
    }

    public void onPersonSelected(String person, int nameImageId, int imageColor, int containerColor,
                                 int choiceTextColor) {

        mName = person;
        mPersonImageId = nameImageId;
        mPeopleImageColor = imageColor;
        mPeopleContainerColor = containerColor;
        mPeopleChoiceColor = choiceTextColor;

    }

    public void onSpotSelected(String location, int locationImageId, int imageColor,
                               int containerColor, int choiceTextColor) {

        mSpot = location;
        mSpotImageId = locationImageId;
        mSpotImageColor = imageColor;
        mSpotContainerColor = containerColor;
        mSpotChoiceColor = choiceTextColor;
    }

    public void onActivitySelected(String activity, int activityImageId, int imageColor,
                                   int containerColor, int choiceTextColor) {

        mActivity = activity;
        mActivityImageId = activityImageId;
        mActivityImageColor = imageColor;
        mActivityContainerColor = containerColor;
        mActivityChoiceColor = choiceTextColor;
    }


    public String getmName() {
        return mName;
    }

    public int getmPersonImageId() {
        return mPersonImageId;
    }

    public String getmSpot() {
        return mSpot;
    }

    public int getmSpotImageId() {
        return mSpotImageId;
    }

    public String getmActivity() {
        return mActivity;
    }

    public int getmActivityImageId() {
        return mActivityImageId;
    }

    public int getmSpotImageColor() {
        return mSpotImageColor;
    }

    public int getmPeopleImageColor() {
        return mPeopleImageColor;
    }

    public int getmActivityImageColor() {
        return mActivityImageColor;
    }

    public int getmSpotContainerColor() {
        return mSpotContainerColor;
    }

    public int getmPeopleContainerColor() {
        return mPeopleContainerColor;
    }

    public int getmActivityContainerColor() {
        return mActivityContainerColor;
    }

    public int getmSpotChoiceColor() {
        return mSpotChoiceColor;
    }

    public int getmPeopleChoiceColor() {
        return mPeopleChoiceColor;
    }

    public int getmActivityChoiceColor() {
        return mActivityChoiceColor;
    }
}
