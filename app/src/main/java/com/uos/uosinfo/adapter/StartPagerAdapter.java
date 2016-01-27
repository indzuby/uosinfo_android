package com.uos.uosinfo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.uos.uosinfo.R;
import com.uos.uosinfo.controller.StartFragment;

/**
 * Created by 주현 on 2016-01-27.
 */
public class StartPagerAdapter extends FragmentPagerAdapter {
    Context mContext;
    int[] mResources = {R.mipmap.main_bg01,R.mipmap.main_bg02,R.mipmap.main_bg03,R.mipmap.main_bg04};
    public StartPagerAdapter(FragmentManager fm,Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mResources.length;
    }

    @Override
    public Fragment getItem(int position) {
        StartFragment startFragment = new StartFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isLast",position == getCount()-1);
        bundle.putInt("resource", mResources[position]);
        startFragment.setArguments(bundle);
        return startFragment;
    }



}
