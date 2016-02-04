package com.uos.uosinfo.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.parse.ParseQuery;
import com.uos.uosinfo.R;
import com.uos.uosinfo.domain.Admission;
import com.uos.uosinfo.domain.Celebrity;
import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.domain.Department;
import com.uos.uosinfo.domain.DepartmentImage;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.domain.Recruitment;
import com.uos.uosinfo.domain.RecruitmentType;
import com.uos.uosinfo.domain.ResultPathFinder;
import com.uos.uosinfo.domain.Word;
import com.uos.uosinfo.utils.CodeDefinition;
import com.uos.uosinfo.utils.DataBaseUtils;
import com.uos.uosinfo.utils.SessionUtils;

import java.util.Date;

/**
 * Created by user on 2016-01-20.
 */
public class StartActivity extends BaseAcitvity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        SessionUtils.putInt(this, CodeDefinition.DATABASE_SYNC, 0);
        databaseSync();
        initSplash();
    }

    public void databaseSync(){
        DataBaseUtils dataBaseUtils = new DataBaseUtils(getBaseContext());
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(Admission.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(Celebrity.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(College.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(Department.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(DepartmentImage.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(PathFinder.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(Recruitment.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(RecruitmentType.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(ResultPathFinder.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(Word.class));
    }
    public void initSplash(){
        ImageView imageView = (ImageView) findViewById(R.id.splash_image);
        Glide.with(this).load(R.mipmap.main_bg01).crossFade().into(imageView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    while (SessionUtils.getInt(StartActivity.this, CodeDefinition.DATABASE_SYNC, 0) < 9) ;
                    gotoMain();

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();
    }
    public void gotoMain(){
        SessionUtils.putBoolean(StartActivity.this, CodeDefinition.SPLASH_VIEW, true);
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
