package com.uos.uosinfo.view.main;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.kakao.auth.Session;
import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.TabPagerAdapter;
import com.uos.uosinfo.ui.MainViewPager;

public class MainActivity extends BaseActivity {
    TabLayout tabLayout;
    TabPagerAdapter adapter;
//    SessionCallback callback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        callback = new SessionCallback();
//        Session.getCurrentSession().addCallback(callback);
//        Session.getCurrentSession().checkAndImplicitOpen();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        final MainViewPager viewPager = (MainViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);
        adapter = new TabPagerAdapter(getSupportFragmentManager(),getBaseContext());
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        for(int i = 0; i<tabLayout.getTabCount();i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }
        selectedTab(0);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedTab(tab.getPosition());
                viewPager.setCurrentItem(tab.getPosition());
                adapter.init(tab.getPosition());
                InputMethodManager imm= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(viewPager.getWindowToken(), 0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void selectedTab(int position){
        for(int i = 0; i<tabLayout.getTabCount();i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            ImageView img = (ImageView)tab.getCustomView().findViewById(R.id.image);
            TextView textView = (TextView) tab.getCustomView().findViewById(R.id.textView);
            textView.setSelected(false);
            img.setSelected(false);
        }
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        ImageView img = (ImageView)tab.getCustomView().findViewById(R.id.image);
        TextView textView = (TextView) tab.getCustomView().findViewById(R.id.textView);
        textView.setSelected(true);
        img.setSelected(true);
        tab.select();
    }
//
//    private class SessionCallback implements ISessionCallback {
//
//        @Override
//        public void onSessionOpened() {
//            adapter.init(2);
//        }
//
//        @Override
//        public void onSessionOpenFailed(KakaoException exception) {
//            if(exception != null) {
//                Logger.e(exception);
//            }
//            AlertPopup alertPopup = new AlertPopup(getBaseContext(),"카카오톡 로그인에 실패했습니다\n카카오톡을 확인해주세요.",null);
//            alertPopup.show();
//        }
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
