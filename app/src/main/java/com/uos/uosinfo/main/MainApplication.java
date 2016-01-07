package com.uos.uosinfo.main;

import android.app.Application;
import android.content.Context;

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
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
