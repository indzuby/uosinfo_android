package com.uos.uosinfo.view.careerfinder;

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
