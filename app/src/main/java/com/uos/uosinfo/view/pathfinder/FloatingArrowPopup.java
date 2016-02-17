package com.uos.uosinfo.view.pathfinder;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uos.uosinfo.R;

/**
 * Created by user on 2016-01-07.
 */
public class FloatingArrowPopup extends Dialog implements View.OnClickListener {

    View.OnClickListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_path_finder_floating);
        setLayout();
        findViewById(R.id.layout).setOnClickListener(this);
    }
    public FloatingArrowPopup(Context context, View.OnClickListener listener) {
        // Dialog 배경을 투명 처리 해준다.
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        mListener = listener;
    }
    private ImageButton mFloatingArrow;
    private LinearLayout mTransLateButton,mBgButton,mLastButton;
    private Animation fab_open;
    private void setLayout(){
        mFloatingArrow = (ImageButton) findViewById(R.id.arrow_button);
        mTransLateButton = (LinearLayout) findViewById(R.id.translate_button);
        mBgButton = (LinearLayout) findViewById(R.id.bg_button);
        mLastButton = (LinearLayout) findViewById(R.id.last_button);

        fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);

        mFloatingArrow.setOnClickListener(this);
        mTransLateButton.setOnClickListener(mListener);
        findViewById(R.id.translate_img_button).setOnClickListener(mListener);
        mBgButton.setOnClickListener(mListener);
        findViewById(R.id.bg_img_button).setOnClickListener(mListener);
        mLastButton.setOnClickListener(mListener);
        findViewById(R.id.last_img_button).setOnClickListener(mListener);

        mTransLateButton.startAnimation(fab_open);
        mBgButton.startAnimation(fab_open);
        mLastButton.startAnimation(fab_open);

        mTransLateButton.setClickable(true);
        mBgButton.setClickable(true);
        mLastButton.setClickable(true);

    }
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.layout:
            case R.id.arrow_button:
                dismiss();
                break;
        }
    }
    public void setLastButton(boolean isThisMonth){
        if(isThisMonth) {
            ((ImageView)findViewById(R.id.last_img_button)).setImageResource(R.mipmap.ab_lastmonth);
            ((TextView)findViewById(R.id.last_text)).setText("지난 달의 패스파인더 보기");
        }else {
            ((ImageView)findViewById(R.id.last_img_button)).setImageResource(R.mipmap.ab_thismonth);
            ((TextView)findViewById(R.id.last_text)).setText("이번 달의 패스파인더 보기");
        }
    }
}
