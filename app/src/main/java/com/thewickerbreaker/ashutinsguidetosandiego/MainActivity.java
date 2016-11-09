package com.thewickerbreaker.ashutinsguidetosandiego;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements PeopleFragment.OnPersonSelectedListener,
        SpotsFragment.OnSpotSelectedListener, ActivitiesFragment.OnActivitySelectedListener {

    private ArrayList<SummaryItems> mSpotArray;
    private ArrayList<SummaryItems> mPeopleArray;
    private ArrayList<SummaryItems> mActivityArray;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);

        // Find the view pager that will allow the user to swipe between fragments
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        assert viewPager != null;
        viewPager.setOffscreenPageLimit(3);

        // Create an adapter that knows which fragment should be shown on each page
        PagerAdapter adapter = new PagerAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Find the tab layout that shows the tabs
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        assert tabLayout != null;
        tabLayout.setupWithViewPager(viewPager);
    }


    public void onSpotSelected(ArrayList<SummaryItems> spotArray) {

        mSpotArray = spotArray;
    }

    public void onActivitySelected(ArrayList<SummaryItems> activityArray) {
        mActivityArray = activityArray;
    }

    public void onPersonSelected(ArrayList<SummaryItems> peopleArray) {
        mPeopleArray = peopleArray;
    }

    public ArrayList<SummaryItems> getmSpotArray() {
        return mSpotArray;
    }

    public ArrayList<SummaryItems> getmActivityArray() {
        return mActivityArray;
    }

    public ArrayList<SummaryItems> getmPeopleArray() {
        return mPeopleArray;
    }
}
