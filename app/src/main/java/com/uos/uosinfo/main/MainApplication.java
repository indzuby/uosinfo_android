package com.uos.uosinfo.main;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.tsengvn.typekit.Typekit;

/**
 * Created by user on 2016-01-07.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addNormal(Typekit.createFromAsset(this, "Spoqa_Han_Sans_Regular.ttf"))
                .addBold(Typekit.createFromAsset(this, "Spoqa_Han_Sans_Bold.ttf"));
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this);

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
