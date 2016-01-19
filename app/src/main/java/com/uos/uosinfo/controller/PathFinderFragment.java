package com.uos.uosinfo.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.PathFinderAdapter;
import com.uos.uosinfo.database.DataBaseUtils;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.main.FloatingPopup;
import com.uos.uosinfo.main.UosFragment;
import com.uos.uosinfo.controller.pathfinder.PathFinderItemFragment;
import com.uos.uosinfo.ui.PagerPoint;
import com.uos.uosinfo.utils.BgUtils;
import com.uos.uosinfo.utils.JsonUtils;
import com.uos.uosinfo.utils.ParseUtils;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2015-12-30.
 */
public class PathFinderFragment extends UosFragment implements View.OnClickListener {
    private ImageButton mFloatingPlus;
    private FloatingPopup mfFloatingPopup;
    ViewPager mViewPager;
    PathFinderAdapter mAdapter;
    List<PathFinder> mPath;
    List<Fragment> mFragments;
    FrameLayout mPathFinderBg;
    int bgRes = R.mipmap.main_bg01;
    LinearLayout mOvalContainer;
    DataBaseUtils mDataBaseUtils;
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
        mDataBaseUtils = new DataBaseUtils(getContext());
        mPathFinderBg = (FrameLayout) mView.findViewById(R.id.path_finder_bg);
        mFloatingPlus = (ImageButton) mView.findViewById(R.id.plus_button);
        mViewPager = (ViewPager) mView.findViewById(R.id.viewPager);
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
        mPath = mDataBaseUtils.selectPathFinderThisMonth();
        setFragments();
        mAdapter = new PathFinderAdapter(getChildFragmentManager(),getContext(), mFragments);
        mViewPager.setAdapter(mAdapter);
        if(mPath.size()<=0)
            getPathFinderThisMonthByParse();
        else
            changeData();

    }
    private void setFragments(){
        mFragments.clear();
        for(PathFinder pathFinder : mPath) {
            Fragment fragment = new PathFinderItemFragment();
            Bundle bundle = new Bundle();
            bundle.putString("pathFinder", JsonUtils.objectToJson(pathFinder));
            fragment.setArguments(bundle);
            mFragments.add(fragment);
        }
    }
    void getPathFinderLastMonthByDataBase(){
        mPath = mDataBaseUtils.selectPathFinderLastMonth();
        if(mPath.size()<=0)
            getPathFinderLastMonthByParse();
        else
            changeData();
    }
    void getPathFinderThisMonthByParse(){
        mDataBaseUtils.getPathFinderByParse(new Date(), thisCallback);
    }
    void getPathFinderLastMonthByParse(){
        mDataBaseUtils.getPathFinderByParse(new DateTime().minusMonths(1).toDate(),lastCallback);
    }
    FindCallback<ParseObject> thisCallback = new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> pathFinders, ParseException e) {
            if (e == null) {
                Log.e("TAG", pathFinders.size() + "");
                mPath = ParseUtils.parsePathFinders(pathFinders);
                mDataBaseUtils.insertPathFinders(mPath);
                changeData();
            } else {
                Log.e("TAG", "Error: " + e.getMessage());
            }
        }
    };
    FindCallback<ParseObject> lastCallback = new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> pathFinders, ParseException e) {
            if (e == null) {
                Log.e("TAG", pathFinders.size() + "");
                mPath = ParseUtils.parsePathFinders(pathFinders);
                mDataBaseUtils.insertPathFinders(mPath);
                changeData();
            } else {
                Log.e("TAG", "Error: " + e.getMessage());
            }
        }
    };
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
