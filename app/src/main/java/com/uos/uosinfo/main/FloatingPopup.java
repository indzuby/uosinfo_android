package com.uos.uosinfo.main;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.uos.uosinfo.R;

/**
 * Created by user on 2016-01-07.
 */
public class FloatingPopup extends Dialog implements View.OnClickListener {

    View.OnClickListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_floating);
        setLayout();
        findViewById(R.id.layout).setOnClickListener(this);
    }
    public FloatingPopup(Context context, View.OnClickListener listener) {
        // Dialog 배경을 투명 처리 해준다.
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        mListener = listener;
    }
    private ImageButton mFloatingPlus;
    private LinearLayout mFloatingSchedule, mFloatingNotice, mFloatingPush , mFloatingPhone;
    private Animation fab_open, rotate_forward, rotate_backward;
    private void setLayout(){
        mFloatingPlus = (ImageButton) findViewById(R.id.plus_button);
        mFloatingSchedule = (LinearLayout) findViewById(R.id.schedule_button);
        mFloatingNotice = (LinearLayout) findViewById(R.id.notice_button);
        mFloatingPush = (LinearLayout) findViewById(R.id.push_button);
        mFloatingPhone = (LinearLayout) findViewById(R.id.phone_button);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);

        rotate_forward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_backward);

        mFloatingPlus.setOnClickListener(this);
        mFloatingSchedule.setOnClickListener(mListener);
        mFloatingNotice.setOnClickListener(mListener);
        mFloatingPhone.setOnClickListener(mListener);
        mFloatingPush.setOnClickListener(mListener);

        mFloatingPlus.startAnimation(rotate_forward);
        mFloatingSchedule.startAnimation(fab_open);
        mFloatingNotice.startAnimation(fab_open);
        mFloatingPush.startAnimation(fab_open);
        mFloatingPhone.startAnimation(fab_open);

        mFloatingSchedule.setClickable(true);
        mFloatingNotice.setClickable(true);
        mFloatingPush.setClickable(true);
        mFloatingPhone.setClickable(true);
    }
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.layout:
            case R.id.plus_button:
                dismiss();
                break;
        }
    }
}
