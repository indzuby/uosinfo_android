package com.uos.uosinfo.main;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.TabPagerAdapter;
import com.uos.uosinfo.ui.MainViewPager;

public class MainActivity extends BaseAcitvity{
    TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        MainViewPager viewPager = (MainViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager(),getBaseContext());
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(false);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        for(int i = 0; i<tabLayout.getTabCount();i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setCustomView(adapter.getTabView(i));
        }
        selectedTab(0);
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
}
