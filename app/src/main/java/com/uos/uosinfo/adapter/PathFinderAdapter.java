package com.uos.uosinfo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.tabs.pathfinder.PathFinderItemFragment;
import com.uos.uosinfo.utils.JsonUtils;

import java.util.List;

/**
 * Created by 주현 on 2016-01-10.
 */
public class PathFinderAdapter extends FragmentPagerAdapter {
    Context mContext;
    List<PathFinder> mPass;

    public PathFinderAdapter(FragmentManager fm, Context mContext, List<PathFinder> mPass) {
        super(fm);
        this.mContext = mContext;
        this.mPass = mPass;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new PathFinderItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("passFinder", JsonUtils.objectToJson(mPass.get(position)));
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public int getCount() {
        return mPass.size();
    }
}
