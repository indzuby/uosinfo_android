package com.uos.uosinfo.view.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.uos.uosinfo.R;
import com.uos.uosinfo.utils.CodeDefinition;

/**
 * Created by user on 2016-01-07.
 */
public class FloatingPopup extends Dialog implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_floating);
        setLayout();
        findViewById(R.id.layout).setOnClickListener(this);
    }
    public FloatingPopup(Context context) {
        // Dialog 배경을 투명 처리 해준다.
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
    }
    private ImageButton mFloatingPlus;
    private LinearLayout mFloatingPhone01, mFloatingNotice, mFloatingPhone02 ;
    private Animation fab_open, rotate_forward;
    private void setLayout(){
        mFloatingPlus = (ImageButton) findViewById(R.id.plus_button);
        mFloatingNotice = (LinearLayout) findViewById(R.id.notice_button);
        mFloatingPhone01 = (LinearLayout) findViewById(R.id.phone_01_button);
        mFloatingPhone02 = (LinearLayout) findViewById(R.id.phone_02_button);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);

        rotate_forward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_forward);

        mFloatingPlus.setOnClickListener(this);
        mFloatingPhone01.setOnClickListener(this);
        mFloatingPhone02.setOnClickListener(this);
        mFloatingNotice.setOnClickListener(this);

        mFloatingPlus.startAnimation(rotate_forward);
        mFloatingPhone01.startAnimation(fab_open);
        mFloatingPhone02.startAnimation(fab_open);
        mFloatingNotice.startAnimation(fab_open);

        mFloatingPhone01.setClickable(true);
        mFloatingPhone02.setClickable(true);
        mFloatingNotice.setClickable(true);
    }
    public void onClick(View v) {
        int id = v.getId();
        Intent intent;
        switch (id) {
            case R.id.layout:
            case R.id.plus_button:
                dismiss();
                break;
            case R.id.notice_button:
                intent = new Intent(getContext(),NoticeActivity.class);
                getContext().startActivity(intent);
                dismiss();
                break;
            case R.id.phone_02_button:
                intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+ CodeDefinition.PHONE_02_LINK));
                getContext().startActivity(intent);
                dismiss();
                break;
            case R.id.phone_01_button:
                intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+ CodeDefinition.PHONE_01_LINK));
                getContext().startActivity(intent);
                dismiss();
                break;
        }
    }
}
