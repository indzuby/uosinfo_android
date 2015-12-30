package com.uos.uosinfo.tabs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.uos.uosinfo.R;

/**
 * Created by user on 2015-12-30.
 */
public class PassFinderFragment extends Fragment implements View.OnClickListener {
    private Boolean isFabOpen = false;
    private FloatingActionButton mFloatingPlus, mFloatingSchedule, mFloatingNotice;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward;

    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_pass_finder, container, false);
        init();
        return mView;
    }

    void init() {
        mFloatingPlus = (FloatingActionButton) mView.findViewById(R.id.plus_button);
        mFloatingSchedule = (FloatingActionButton) mView.findViewById(R.id.schedule_button);
        mFloatingNotice = (FloatingActionButton) mView.findViewById(R.id.notice_button);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_backward);
        mFloatingPlus.setOnClickListener(this);
        mFloatingSchedule.setOnClickListener(this);
        mFloatingNotice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.plus_button:
                animateFAB();
                break;
            case R.id.schedule_button:
                break;
            case R.id.notice_button:
                break;
        }
    }

    public void animateFAB() {
        if (isFabOpen) {
            mFloatingPlus.startAnimation(rotate_backward);
            mFloatingSchedule.startAnimation(fab_close);
            mFloatingNotice.startAnimation(fab_close);
            mFloatingSchedule.setClickable(false);
            mFloatingNotice.setClickable(false);
            isFabOpen = false;
        } else {
            mFloatingPlus.startAnimation(rotate_forward);
            mFloatingSchedule.startAnimation(fab_open);
            mFloatingNotice.startAnimation(fab_open);
            mFloatingSchedule.setClickable(true);
            mFloatingNotice.setClickable(true);
            isFabOpen = true;
        }
    }
}
