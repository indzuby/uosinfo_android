package com.uos.uosinfo.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.parse.ParseQuery;
import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.StartPagerAdapter;
import com.uos.uosinfo.utils.CodeDefinition;
import com.uos.uosinfo.domain.Admission;
import com.uos.uosinfo.domain.Celebrity;
import com.uos.uosinfo.domain.College;
import com.uos.uosinfo.domain.Department;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.domain.Recruitment;
import com.uos.uosinfo.domain.RecruitmentType;
import com.uos.uosinfo.domain.Word;
import com.uos.uosinfo.utils.DataBaseUtils;
import com.uos.uosinfo.utils.SessionUtils;

import java.util.Date;

/**
 * Created by user on 2016-01-20.
 */
public class StartActivity extends BaseAcitvity{
    ViewPager mViewPager ;
    StartPagerAdapter startPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        boolean splashCheck = SessionUtils.getBoolean(this, CodeDefinition.SPLASH_VIEW,false);
        if(splashCheck)
            gotoMain();
        else {
            databaseSync();
            initSplash();
        }
    }

    public void databaseSync(){
        DataBaseUtils dataBaseUtils = new DataBaseUtils(getBaseContext());
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(Admission.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(Celebrity.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(College.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(Department.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(PathFinder.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(Recruitment.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(RecruitmentType.class));
        dataBaseUtils.getMoreDatabase(new Date(), ParseQuery.getQuery(Word.class));
    }
    public void initSplash(){
        mViewPager = (ViewPager) findViewById(R.id.start_view_pager);
        startPagerAdapter = new StartPagerAdapter(getSupportFragmentManager(),this);
        mViewPager.setAdapter(startPagerAdapter);
    }

    public void gotoMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();

    }

}
