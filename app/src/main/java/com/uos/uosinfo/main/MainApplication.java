package com.uos.uosinfo.main;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.tsengvn.typekit.Typekit;
import com.uos.uosinfo.domain.Admission;
import com.uos.uosinfo.domain.Celebrity;
import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.domain.Department;
import com.uos.uosinfo.domain.DepartmentImage;
import com.uos.uosinfo.domain.Library;
import com.uos.uosinfo.domain.Notice;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.domain.QnaBoard;
import com.uos.uosinfo.domain.Recruitment;
import com.uos.uosinfo.domain.RecruitmentType;
import com.uos.uosinfo.domain.ResultPathFinder;
import com.uos.uosinfo.domain.Word;

/**
 * Created by user on 2016-01-07.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Typekit.getInstance()
                .addCustom1(Typekit.createFromAsset(this, "Spoqa_Han_Sans_Thin.ttf"))
                .addNormal(Typekit.createFromAsset(this, "Spoqa_Han_Sans_Regular.ttf"))
                .addBold(Typekit.createFromAsset(this, "Spoqa_Han_Sans_Bold.ttf"));
        registParse();
    }
    public void registParse(){

        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        ParseObject.registerSubclass(Admission.class);
        ParseObject.registerSubclass(Celebrity.class);
        ParseObject.registerSubclass(College.class);
        ParseObject.registerSubclass(Department.class);
        ParseObject.registerSubclass(DepartmentImage.class);
        ParseObject.registerSubclass(Library.class);
        ParseObject.registerSubclass(Notice.class);
        ParseObject.registerSubclass(PathFinder.class);
        ParseObject.registerSubclass(QnaBoard.class);
        ParseObject.registerSubclass(Recruitment.class);
        ParseObject.registerSubclass(RecruitmentType.class);
        ParseObject.registerSubclass(ResultPathFinder.class);
        ParseObject.registerSubclass(Word.class);

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
