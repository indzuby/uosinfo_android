package com.uos.uosinfo.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.uos.uosinfo.R;
import com.uos.uosinfo.adapter.QnaAdapter;
import com.uos.uosinfo.domain.QnaBoard;
import com.uos.uosinfo.utils.CodeDefinition;
import com.uos.uosinfo.utils.DataBaseUtils;
import com.uos.uosinfo.utils.SessionUtils;
import com.uos.uosinfo.view.common.AlertPopup;
import com.uos.uosinfo.view.main.UosFragment;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by user on 2015-12-30.
 */
public class QnaFragment extends UosFragment implements View.OnClickListener{
    private SessionCallback callback;
    String accessToken;
    LinearLayout mKakaoPopup;
    LinearLayout mConfirmPopup;
    RelativeLayout mQnaBoard;
    List<QnaBoard> mQna;
    DataBaseUtils mDataBaseUtils;
    QnaAdapter mQnaAdapter;
    EditText mQnaEditText;
    ListView mQnaList;
    ImageButton mSend;
    DateTime lastQnaTime;
    int qnaCount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_qna, container, false);
        return mView;
    }
    public void init(){
        accessToken = Session.getCurrentSession().getAccessToken();
        mDataBaseUtils = new DataBaseUtils(getContext());
        mKakaoPopup = (LinearLayout) mView.findViewById(R.id.kakao_popup);
        mConfirmPopup = (LinearLayout) mView.findViewById(R.id.confirm_popup);
        mQnaBoard = (RelativeLayout) mView.findViewById(R.id.qna_board);
        initAcceptPopup();

    }
    public void initQnaBoard(){
        lastQnaTime = new DateTime(mDataBaseUtils.getLastQna(accessToken));
        qnaCount = SessionUtils.getInt(getContext(), CodeDefinition.QNA_COUNT, 0);
        mKakaoPopup.setVisibility(View.GONE);
        mConfirmPopup.setVisibility(View.GONE);
        mQnaBoard.setVisibility(View.VISIBLE);
        mQnaEditText = (EditText) mView.findViewById(R.id.qna_edittext);
        mQnaList = (ListView) mView.findViewById(R.id.qna_list);
        mSend = (ImageButton) mView.findViewById(R.id.send);
        mQna = mDataBaseUtils.getQna();
        mQnaAdapter = new QnaAdapter(getContext(),mQna);
        mQnaList.setAdapter(mQnaAdapter);
        mQnaList.setSelection(mQna.size()-1);
        mSend.setOnClickListener(this);
        mQnaEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0)
                    mSend.setSelected(true);
                else
                    mSend.setSelected(false);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public int getIndexItem(String objectId) {
        for (int i=0;i<mQna.size();i++ ) {
            QnaBoard qnaBoard = mQna.get(i);
            if (qnaBoard.getObjectId().equals(objectId))
                return i;
        }
        return -1;
    }

    public void getMoreQna(){
        Date lastDate = getLastDate();
        List<QnaBoard> qnas = mDataBaseUtils.getMoreQna(lastDate);
        try {
            ParseObject.pinAll(qnas);
        }catch (ParseException e2) {
            e2.printStackTrace();
        }
        List<QnaBoard> deleteIndex = new ArrayList<>();
        for (QnaBoard qna : qnas) {
            int index = getIndexItem(qna.getObjectId());
            if(index == -1)
                mQna.add(0, qna);
            else {
                if(!qna.getValid())
                    deleteIndex.add(qna);
                mQna.set(index, qna);
            }
        }
        mQna.removeAll(deleteIndex);
        mQnaAdapter.notifyDataSetChanged();
        mQnaList.setSelection(mQna.size()-1);
    }
    public Date getLastDate(){
        if(mQna==null || mQna.isEmpty())
            return null;
        return mDataBaseUtils.getLastQna();
    }
    public boolean checkAllowQna(){
        DateTime currentDate = new DateTime();
        if(lastQnaTime==null)
            return true;
        if(Days.daysBetween(lastQnaTime, currentDate).getDays()>=1) {
            qnaCount=0;
            return true;
        }
        else if(Minutes.minutesBetween(lastQnaTime, currentDate).getMinutes()>5)
            if(qnaCount<3)
                return true;
            else
                return false;
        else
            return false;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.send) {
            if(v.isSelected()){

                if(checkAllowQna()) {

                    QnaBoard qna = mDataBaseUtils.sendQna(mQnaEditText.getText().toString(), accessToken);
                    mQna.add(qna);
                    mQnaAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), "질문이 등록되었습니다.", Toast.LENGTH_LONG).show();
                    mQnaEditText.setText("");
                    mQnaList.setSelection(mQna.size() - 1);
                    qnaCount++;
                    DateTime time = new DateTime();
                    lastQnaTime = time;
                    SessionUtils.putInt(getContext(),CodeDefinition.QNA_COUNT,qnaCount);
                }else {
                    AlertPopup popup = new AlertPopup(getActivity(),getString(R.string.qna_count_alert),null);
                    popup.setButtonText("확인");
                    popup.show();
                }
            }
        }
    }

    public void initAcceptPopup(){
        mKakaoPopup.setVisibility(View.GONE);
        mConfirmPopup.setVisibility(View.VISIBLE);
        mQnaBoard.setVisibility(View.GONE);
        TextView contents = (TextView) mView.findViewById(R.id.contents);
        TextView yes = (TextView) mView.findViewById(R.id.yes);
        TextView no = (TextView) mView.findViewById(R.id.no);
        contents.setText(getString(R.string.qna_confirm_contents));
        yes.setText(getString(R.string.qna_confirm_yes));
        no.setText(getString(R.string.qna_confirm_no));
        yes.setOnClickListener(qnaListener);
        no.setOnClickListener(qnaListener);

    }
    public void initKakaoLogin(){
        mKakaoPopup.setVisibility(View.VISIBLE);
        mConfirmPopup.setVisibility(View.GONE);
        mQnaBoard.setVisibility(View.GONE);
        callback = new SessionCallback();
        Session.getCurrentSession().addCallback(callback);
        Session.getCurrentSession().checkAndImplicitOpen();

    }
    View.OnClickListener qnaListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.yes) {
                if(accessToken!=null && !accessToken.isEmpty()) {
                    initQnaBoard();
                }else
                    initKakaoLogin();
            }else if(v.getId() == R.id.no) {
                mConfirmPopup.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }
    private class SessionCallback implements ISessionCallback {

        @Override
        public void onSessionOpened() {
            accessToken = Session.getCurrentSession().getAccessToken();
            Toast.makeText(getContext(),"카카오톡 로그인에 성공했습니다",Toast.LENGTH_SHORT).show();
            initQnaBoard();
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) {
                Logger.e(exception);
            }
            Toast.makeText(getContext(),"카카오톡 로그인에 실패했습니다\n카카오톡을 확인해주세요.",Toast.LENGTH_SHORT).show();
        }
    }
}
