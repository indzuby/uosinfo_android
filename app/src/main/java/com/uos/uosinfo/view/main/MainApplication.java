package com.uos.uosinfo.view.main;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.kakao.auth.ApprovalType;
import com.kakao.auth.AuthType;
import com.kakao.auth.IApplicationConfig;
import com.kakao.auth.ISessionConfig;
import com.kakao.auth.KakaoAdapter;
import com.kakao.auth.KakaoSDK;
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
    private static volatile MainApplication instance = null;
    private static volatile Activity currentActivity = null;
    public static MainApplication getGlobalApplicationContext() {
        if(instance == null)
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Typekit.getInstance()
                .addItalic(Typekit.createFromAsset(this, "Spoqa_Han_Sans_Thin.ttf"))
                .addNormal(Typekit.createFromAsset(this, "Spoqa_Han_Sans_Regular.ttf"))
                .addBold(Typekit.createFromAsset(this, "Spoqa_Han_Sans_Bold.ttf"));
        registParse();

        KakaoSDK.init(new KakaoSDKAdapter());
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
    private static class KakaoSDKAdapter extends KakaoAdapter {
        /**
         * Session Config에 대해서는 default값들이 존재한다.
         * 필요한 상황에서만 override해서 사용하면 됨.
         * @return Session의 설정값.
         */
        @Override
        public ISessionConfig getSessionConfig() {
            return new ISessionConfig() {
                @Override
                public AuthType[] getAuthTypes() {
                    return new AuthType[] {AuthType.KAKAO_LOGIN_ALL};
                }

                @Override
                public boolean isUsingWebviewTimer() {
                    return false;
                }

                @Override
                public ApprovalType getApprovalType() {
                    return ApprovalType.INDIVIDUAL;
                }

                @Override
                public boolean isSaveFormData() {
                    return true;
                }
            };
        }

        @Override
        public IApplicationConfig getApplicationConfig() {
            return new IApplicationConfig() {
                @Override
                public Activity getTopActivity() {
                    return MainApplication.getCurrentActivity();
                }

                @Override
                public Context getApplicationContext() {
                    return MainApplication.getGlobalApplicationContext();
                }
            };
        }
    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    // Activity가 올라올때마다 Activity의 onCreate에서 호출해줘야한다.
    public static void setCurrentActivity(Activity currentActivity) {
        MainApplication.currentActivity = currentActivity;
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
}
