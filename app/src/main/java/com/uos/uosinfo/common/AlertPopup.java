package com.uos.uosinfo.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.uos.uosinfo.R;

/**
 * Created by 주현 on 2016-01-18.
 */
public class AlertPopup extends Dialog implements View.OnClickListener{
    String mContents;
    String mYes;
    View.OnClickListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_alert);
        setLayout();
        findViewById(R.id.layout).setOnClickListener(this);
    }

    public AlertPopup(Context context, String contents,View.OnClickListener listener) {

        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        mContents = contents;
        mListener = listener;
        mYes = "네";
    }
    public void setButtonText(String yes){
        mYes = yes;
    }
    private void setLayout(){
        TextView contents = (TextView) findViewById(R.id.contents);
        TextView yes = (TextView) findViewById(R.id.yes);

        yes.setOnClickListener(mListener);

        contents.setText(mContents);
        yes.setText(mYes);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.layout:
                dismiss();
                break;
        }

    }
}
