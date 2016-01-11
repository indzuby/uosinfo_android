package com.uos.uosinfo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.tabs.pathfinder.PathFinderItemFragment;
import com.uos.uosinfo.utils.JsonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 주현 on 2016-01-10.
 */
public class PathFinderAdapter extends FragmentPagerAdapter {
    Context mContext;
    List<PathFinder> mPass;
    boolean language;
    HashMap<Integer,Fragment> fragmentMap;
    public PathFinderAdapter(FragmentManager fm, Context mContext, List<PathFinder> mPass) {
        super(fm);
        this.mContext = mContext;
        this.mPass = mPass;
        fragmentMap = new HashMap<>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = new PathFinderItemFragment();
        Bundle bundle = new Bundle();
        bundle.putString("passFinder", JsonUtils.objectToJson(mPass.get(position)));
        fragment.setArguments(bundle);
        if(!fragmentMap.containsKey(position)) fragmentMap.put(position,fragment);
        return fragment;
    }
    @Override
    public int getCount() {
        return mPass.size();
    }

    public void changeLanguage(boolean language){
        this.language = language;
        for(int i = 0; i <fragmentMap.size();i++) {
            PathFinderItemFragment fragment = (PathFinderItemFragment) fragmentMap.get(i);
            fragment.setLanguage(language);
        }
    }

}
