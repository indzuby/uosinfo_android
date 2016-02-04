package com.uos.uosinfo.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.PathFinderAdapter;
import com.uos.uosinfo.controller.pathfinder.PathFinderItemFragment;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.main.FloatingPopup;
import com.uos.uosinfo.main.UosFragment;
import com.uos.uosinfo.service.PathFinderService;
import com.uos.uosinfo.ui.PagerPoint;
import com.uos.uosinfo.utils.BgUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015-12-30.
 */
public class PathFinderFragment extends UosFragment implements View.OnClickListener {
    private ImageButton mFloatingPlus;
    private FloatingPopup mfFloatingPopup;
    PathFinderService mPathFinderService;
    ViewPager mViewPager;
    PathFinderAdapter mAdapter;
    List<PathFinder> mPath;
    List<Fragment> mFragments;
    FrameLayout mPathFinderBg;
    int bgRes = R.mipmap.main_bg01;
    LinearLayout mOvalContainer;
    private boolean language = false; // false : En , true : Ko;
    private boolean isThisMonth = true; // false : lastMonth, true : thisMonth\

    public boolean isLanguage() {
        return language;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_path_finder, container, false);
        init();
        return mView;
    }

    public void init() {
        mPathFinderService = new PathFinderService(getContext());
        mPathFinderBg = (FrameLayout) mView.findViewById(R.id.path_finder_bg);
        mFloatingPlus = (ImageButton) mView.findViewById(R.id.plus_button);
        mViewPager = (ViewPager) mView.findViewById(R.id.pathfinder_view_pager);
        mOvalContainer = (LinearLayout) mView.findViewById(R.id.oval_container);
        mFloatingPlus.setOnClickListener(this);
        mFragments = new ArrayList<>();
        mPathFinderBg.setBackground(getActivity().getDrawable(bgRes));
        getPathFinderThisMonthByDataBase();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                changeOvalState(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    void getPathFinderThisMonthByDataBase(){
        mPath = mPathFinderService.getPathFinderThisMonthByDataBase();
        setFragments();
        mAdapter = new PathFinderAdapter(getChildFragmentManager(),getContext(), mFragments);
        mViewPager.setAdapter(mAdapter);
        changeData();
    }
    void getPathFinderLastMonthByDataBase(){
        mPath = mPathFinderService.getPathFinderLastMonthByDataBase();
        changeData();
    }
    private void setFragments(){
        mFragments.clear();
        for(PathFinder pathFinder : mPath) {
            Fragment fragment = new PathFinderItemFragment();
            Bundle bundle = new Bundle();
            bundle.putString("objectId", pathFinder.getObjectId());
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }
    }
    private void changeData(){
        mViewPager.setOffscreenPageLimit(mFragments.size() - 1);
        setFragments();
        mAdapter.notifyDataSetChanged();
        setOvalContainer();
        mViewPager.setCurrentItem(0);
        changeOvalState(0);
    }
    private void setOvalContainer(){
        mOvalContainer.removeAllViews();
        for(int i = 0; i < mPath.size();i++) {
            mOvalContainer.addView(PagerPoint.getPoint(getActivity()));
        }
    }
    private void changeOvalState(int index){
        for(int i = 0; i<mOvalContainer.getChildCount();i++)
            mOvalContainer.getChildAt(i).setSelected(false);
        mOvalContainer.getChildAt(index).setSelected(true);
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.schedule_button:
                    break;
                case R.id.notice_button:
                    break;
                case R.id.push_button:
                    break;
                case R.id.phone_button:
                    break;
            }

        }
    };
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.plus_button:
                mfFloatingPopup = new FloatingPopup(getContext(),listener);
                mfFloatingPopup.show();
                break;
        }
    }
    public void changeBg(){
        bgRes  = BgUtils.getOtherBg(bgRes);
        mPathFinderBg.setBackground(getActivity().getDrawable(bgRes));
    }
    public void changeLanguage(){
        language = !language;
        mAdapter.changeLanguage(language);
    }
    public void lastPathFinder(){
        mPath.clear();

        if(isThisMonth) {
            getPathFinderLastMonthByDataBase();
        }else {
            getPathFinderThisMonthByDataBase();
        }
        isThisMonth = !isThisMonth;
    }

    public boolean isThisMonth() {
        return isThisMonth;
    }
}
