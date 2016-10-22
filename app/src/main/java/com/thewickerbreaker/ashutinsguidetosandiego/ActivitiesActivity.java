package com.thewickerbreaker.ashutinsguidetosandiego;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Bunker on 10/17/2016.
 */

public class ActivitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ActivitiesFragment())
                .commit();
    }


}
