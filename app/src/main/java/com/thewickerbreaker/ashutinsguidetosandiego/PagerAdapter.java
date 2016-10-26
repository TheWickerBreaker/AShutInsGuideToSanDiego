package com.thewickerbreaker.ashutinsguidetosandiego;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

/**
 * Created by Bunker on 10/17/2016.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private Context mContext;



    public PagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new SummaryFragment();
        } else if (position == 1){
            return new SpotsFragment();
        } else if (position == 2){
            return new ActivitiesFragment();
        } else {
            return new PeopleFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.summary_tab);
        } else if (position == 1) {
            return mContext.getString(R.string.spots_tab);
        } else if (position == 2) {
            return mContext.getString(R.string.activities_tab);
        } else {
            return mContext.getString(R.string.people_tab);
        }
    }


}
