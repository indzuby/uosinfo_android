package com.uos.uosinfo.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.PathFinderAdapter;
import com.uos.uosinfo.domain.PathFinder;
import com.uos.uosinfo.main.FloatingPopup;

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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_path_finder, container, false);
        init();
        return mView;
    }

    void init() {
        mFloatingPlus = (ImageButton) mView.findViewById(R.id.plus_button);
        mViewPager = (ViewPager) mView.findViewById(R.id.viewPager);
        mFloatingPlus.setOnClickListener(this);

        getPassFinderData();
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
                } else {
                    Log.e("TAG", "Error: " + e.getMessage());
                }
            }
        });
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

}
