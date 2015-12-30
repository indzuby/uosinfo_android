package com.uos.uosinfo.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
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

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
