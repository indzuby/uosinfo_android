package com.uos.uosinfo.view.common;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.uos.uosinfo.R;

/**
 * Created by 주현 on 2016-01-18.
 */
public class ConfirmPopup extends Dialog implements View.OnClickListener{
    String mContents;
    String mYes;
    String mNo;
    View.OnClickListener mListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_confirm);
        setLayout();
        findViewById(R.id.layout).setOnClickListener(this);
    }

    public ConfirmPopup(Context context,String contents,View.OnClickListener listener) {

        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        mContents = contents;
        mListener = listener;
        mYes = "네";
        mNo = "아니오";
    }
    public void setTitle(String title) {
        TextView titleView = (TextView)findViewById(R.id.title);
        titleView.setText(title);
        titleView.setVisibility(View.VISIBLE);
    }
    public void setButtonText(String yes, String no){
        mYes = yes;
        mNo = no;
    }
    private void setLayout(){
        TextView contents = (TextView) findViewById(R.id.contents);
        TextView yes = (TextView) findViewById(R.id.yes);
        TextView no = (TextView) findViewById(R.id.no);

        yes.setOnClickListener(mListener);
        no.setOnClickListener(mListener);

        contents.setText(mContents);
        yes.setText(mYes);
        no.setText(mNo);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.no:
            case R.id.layout:
                dismiss();
                break;
        }

    }
}
