package com.uos.uosinfo.tabs;

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
import com.parse.ParseQuery;
import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.PathFinderAdapter;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.main.FloatingPopup;
import com.uos.uosinfo.ui.PagerPoint;
import com.uos.uosinfo.utils.BgUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 2015-12-30.
 */
public class PathFinderFragment extends Fragment implements View.OnClickListener {
    private ImageButton mFloatingPlus;
    private FloatingPopup mfFloatingPopup;
    View mView;
    ViewPager mViewPager;
    PathFinderAdapter mAdapter;
    List<PathFinder> mPass;
    FrameLayout mPathFinderBg;
    int bgRes = R.mipmap.main_bg01;
    LinearLayout mOvalContainer;
    private boolean language = false; // false : En , true : Ko;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_path_finder, container, false);
        init();
        return mView;
    }

    void init() {
        mPathFinderBg = (FrameLayout) mView.findViewById(R.id.path_finder_bg);
        mFloatingPlus = (ImageButton) mView.findViewById(R.id.plus_button);
        mViewPager = (ViewPager) mView.findViewById(R.id.viewPager);
        mOvalContainer = (LinearLayout) mView.findViewById(R.id.oval_container);
        mFloatingPlus.setOnClickListener(this);

        mPathFinderBg.setBackground(getActivity().getDrawable(bgRes));
        getPassFinderData();
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
    void getPassFinderData(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("PathFinder");
        query.whereLessThanOrEqualTo("startDatetime",new Date()).whereGreaterThanOrEqualTo("endDatetime",new Date()).findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> pathFinders, ParseException e) {
                if (e == null) {
                    Log.e("TAG", pathFinders.size() + "");
                    mPass = PathFinder.PasreUtil.parseLibrary(pathFinders);
                    mAdapter = new PathFinderAdapter(getChildFragmentManager(), getActivity(), mPass);
                    mViewPager.setOffscreenPageLimit(mPass.size() - 1);
                    mViewPager.setAdapter(mAdapter);
                    setOvalContainer();
                    changeOvalState(0);
                } else {
                    Log.e("TAG", "Error: " + e.getMessage());
                }
            }
        });
    }
    private void setOvalContainer(){
        mOvalContainer.removeAllViews();
        for(int i = 0; i <mPass.size();i++) {
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
}
