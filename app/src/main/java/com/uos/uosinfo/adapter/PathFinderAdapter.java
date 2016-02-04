package com.uos.uosinfo.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.uos.uosinfo.common.PathFinderInterface;

import java.util.List;

/**
 * Created by 주현 on 2016-01-10.
 */
public class PathFinderAdapter extends FragmentStatePagerAdapter {
    Context mContext;
    List<Fragment> mPath;
    boolean language;
    public PathFinderAdapter(FragmentManager fm, Context mContext, List<Fragment> mPath) {
        super(fm);
        this.mContext = mContext;
        this.mPath = mPath;
    }

    @Override
    public Fragment getItem(int position) {
        return mPath.get(position);
    }
    @Override
    public int getCount() {
        return mPath.size();
    }

    public void changeLanguage(boolean language){
        this.language = language;
        for(int i = 0; i <mPath.size();i++) {
            PathFinderInterface fragment = (PathFinderInterface) mPath.get(i);
            fragment.setLanguage(language);
        }
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
