package com.uos.uosinfo.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.uos.uosinfo.R;
import com.uos.uosinfo.view.common.AlertPopup;
import com.uos.uosinfo.view.common.ConfirmPopup;
import com.uos.uosinfo.view.main.UosFragment;
import com.uos.uosinfo.view.qna.KakaoLoginPopup;

/**
 * Created by user on 2015-12-30.
 */
public class QnaFragment extends UosFragment {
    private SessionCallback callback;
    ConfirmPopup confirmPopup ;
    KakaoLoginPopup kakaoLoginPopup;
    String accessToken;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_qna,container,false);
        init();
        return mView;
    }
    public void init(){
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();
        confirmPopup = new ConfirmPopup(getContext(),getString(R.string.qna_confirm_contents),qnaListener);
        confirmPopup.setButtonText(getString(R.string.qna_confirm_yes), getString(R.string.qna_confirm_no));
        kakaoLoginPopup = new KakaoLoginPopup(getContext(),kakaoHandler);
    }
    public void initQnaBoard(){

    }
    Handler kakaoHandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0 ){
                accessToken = (String)msg.obj;
                initQnaBoard();
            }else {
                AlertPopup alertPopup = new AlertPopup(getContext(),"카카오톡 로그인에 실패했습니다\n카카오톡을 확인해주세요.",null);
                alertPopup.show();
            }
        }
    };
    View.OnClickListener qnaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.yes) {
                kakaoLoginPopup.show();
            }else
                confirmPopup.dismiss();
        }
    };

    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            accessToken = Session.getCurrentSession().getAccessToken();
            initQnaBoard();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
            confirmPopup.show();
        }
    }
}
