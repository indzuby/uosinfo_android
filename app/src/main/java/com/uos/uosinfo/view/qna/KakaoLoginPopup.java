package com.uos.uosinfo.view.qna;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.usermgmt.LoginButton;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.uos.uosinfo.R;


/**
 * Created by 주현 on 2016-01-18.
 */
public class KakaoLoginPopup extends Dialog implements View.OnClickListener{
    private SessionCallback callback;

    Handler mAHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_kakao);
        setLayout();
        findViewById(R.id.layout).setOnClickListener(this);

        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    public KakaoLoginPopup(Context context,Handler handler) {

        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        mAHandler = handler;
    }
    private void setLayout(){
        TextView contents = (TextView) findViewById(R.id.contents);
        LoginButton loginButton = (LoginButton) findViewById(R.id.com_kakao_login);
        TextView no = (TextView) findViewById(R.id.no);

        no.setOnClickListener(this);
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

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            Message msg = new Message();
            msg.what = 0;
            msg.obj = Session.getCurrentSession().getAccessToken();
            mAHandler.sendMessage(msg);
            dismiss();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
        }
    }
}
