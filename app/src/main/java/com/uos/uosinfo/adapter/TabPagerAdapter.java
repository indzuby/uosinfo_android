package com.uos.uosinfo.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.tabs.CareerFinderFragment;
import com.uos.uosinfo.tabs.InformationFragment;
import com.uos.uosinfo.tabs.LibraryFragment;
import com.uos.uosinfo.tabs.PassFinderFragment;
import com.uos.uosinfo.tabs.QnaFragment;

/**
 * Created by user on 2015-12-30.
 */
public class TabPagerAdapter extends FragmentPagerAdapter{
    Fragment mPass = null,mCareer= null,mQna= null,mInfo= null,mLibrary= null;
    String[] title ={"패스파인더","진로탐색기","진학진로 상담","학부 · 과 안내","자료실"};
    int[] imageResId={R.drawable.menu01_selector,R.drawable.menu02_selector,R.drawable.menu03_selector,R.drawable.menu04_selector,R.drawable.menu05_selector};
    Context mContext;
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public TabPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if(mPass ==null)
                    mPass = new PassFinderFragment();
                return mPass;
            case 1:
                if(mCareer ==null)
                    mCareer = new CareerFinderFragment();
                return mCareer;
            case 2:
                if(mQna ==null)
                    mQna = new QnaFragment();
                return mQna;
            case 3:
                if(mInfo ==null)
                    mInfo = new InformationFragment();
                return mInfo;
            case 4:
                if(mLibrary ==null)
                    mLibrary = new LibraryFragment();
                return mLibrary;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
    public View getTabView(int position) {
        // Given you have a custom layout in `res/layout/custom_tab.xml` with a TextView and ImageView
        View v = LayoutInflater.from(mContext).inflate(R.layout.tab_menu, null);
        TextView tv = (TextView) v.findViewById(R.id.textView);
        tv.setText(title[position]);
        ImageView img = (ImageView) v.findViewById(R.id.image);
        img.setImageResource(imageResId[position]);
        return v;
    }
}
