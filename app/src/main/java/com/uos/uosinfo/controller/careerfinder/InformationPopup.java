package com.uos.uosinfo.controller.careerfinder;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.uos.uosinfo.R;

/**
 * Created by 주현 on 2016-01-18.
 */
public class InformationPopup extends Dialog implements View.OnClickListener {

    View.OnClickListener mListener;
    int mStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.element_career_popup);
        setLayout();
        findViewById(R.id.layout).setOnClickListener(this);
    }

    public InformationPopup(Context context, View.OnClickListener listener) {

        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        mListener = listener;
        mStep = 1;

    }

    private void setLayout() {
        TextView contentsView = (TextView) findViewById(R.id.contents);
        contentsView.setText(getContext().getString(R.string.career_find_popup_1));
        findViewById(R.id.step1).setSelected(true);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.next).setOnClickListener(mListener);
    }

    public boolean setNextStep() {
        TextView contentsView = (TextView) findViewById(R.id.contents);
        mStep++;
        if(mStep>3) {
            return true;
        }
        switch (mStep) {
            case 1:
                contentsView.setText(getContext().getString(R.string.career_find_popup_1));
                break;
            case 2:
                contentsView.setText(getContext().getString(R.string.career_find_popup_2));
                break;
            case 3:
                contentsView.setText(getContext().getString(R.string.career_find_popup_3));
                break;
        }
        if (mStep >= 1)
            findViewById(R.id.step1).setSelected(true);
        if (mStep >= 2)
            findViewById(R.id.step2).setSelected(true);
        if(mStep>=3) {
            findViewById(R.id.step3).setSelected(true);
            TextView next = (TextView) findViewById(R.id.next);
            next.setText("검사 시작");
        }
        findViewById(R.id.cancel).setOnClickListener(this);
        return false;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.cancel:
            case R.id.layout:
                dismiss();
                break;

        }
    }
}
