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
    private void setLayout(){
        ImageButton mFloatingPlus;
        LinearLayout phone01, notice, phone02,web01,web02,web03;
        Animation fab_open, rotate_forward;
        mFloatingPlus = (ImageButton) findViewById(R.id.plus_button);
        notice = (LinearLayout) findViewById(R.id.notice_button);
        phone01 = (LinearLayout) findViewById(R.id.phone_01_button);
        phone02 = (LinearLayout) findViewById(R.id.phone_02_button);
        web01 = (LinearLayout) findViewById(R.id.web_01_button);
        web02 = (LinearLayout) findViewById(R.id.web_02_button);
        web03 = (LinearLayout) findViewById(R.id.web_03_button);
        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);

        rotate_forward = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_forward);

        mFloatingPlus.setOnClickListener(this);
        phone01.setOnClickListener(this);
        phone02.setOnClickListener(this);
        notice.setOnClickListener(this);
        web01.setOnClickListener(this);
        web02.setOnClickListener(this);
        web03.setOnClickListener(this);

        mFloatingPlus.startAnimation(rotate_forward);
        phone01.startAnimation(fab_open);
        phone02.startAnimation(fab_open);
        notice.startAnimation(fab_open);
        web01.startAnimation(fab_open);
        web02.startAnimation(fab_open);
        web03.startAnimation(fab_open);

        phone01.setClickable(true);
        phone02.setClickable(true);
        notice.setClickable(true);
        web01.setClickable(true);
        web02.setClickable(true);
        web03.setClickable(true);
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
                break;
            case R.id.phone_02_button:
                intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+ CodeDefinition.PHONE_02_LINK));
                getContext().startActivity(intent);
                break;
            case R.id.phone_01_button:
                intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+ CodeDefinition.PHONE_01_LINK));
                getContext().startActivity(intent);
                break;
            case R.id.web_01_button:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(CodeDefinition.WEB_01_LINK));
                getContext().startActivity(intent);
                break;
            case R.id.web_02_button:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(CodeDefinition.WEB_02_LINK));
                getContext().startActivity(intent);
                break;
            case R.id.web_03_button:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(CodeDefinition.WEB_03_LINK));
                getContext().startActivity(intent);
                break;

        }
    }
}
