package com.uos.uosinfo.view.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.parse.ParseAnalytics;
import com.tsengvn.typekit.TypekitContextWrapper;

/**
 * Created by user on 2016-01-07.
 */
public class BaseAcitvity extends AppCompatActivity {
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(TypekitContextWrapper.wrap(newBase));
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
