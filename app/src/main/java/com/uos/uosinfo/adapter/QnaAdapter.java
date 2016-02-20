package com.uos.uosinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.domain.QnaBoard;

import java.util.List;

/**
 * Created by 주현 on 2016-02-20.
 */
public class QnaAdapter extends BaseAdapter {
    Context mContext;
    List<QnaBoard> mQna;

    public QnaAdapter(Context mContext, List<QnaBoard> mQna) {
        this.mContext = mContext;
        this.mQna = mQna;
    }

    @Override
    public int getCount() {
        return mQna.size();
    }

    @Override
    public QnaBoard getItem(int position) {
        return mQna.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.element_qna, null);
            convertView = v;
        }
        TextView question = (TextView) convertView.findViewById(R.id.question);
        TextView answer = (TextView) convertView.findViewById(R.id.answer);
        QnaBoard qna = getItem(position);
        question.setText(qna.getQuestion());
        String ans = qna.getAnswer();
        if(ans==null || ans.isEmpty())
            answer.setVisibility(View.GONE);
        else {
            answer.setVisibility(View.VISIBLE);
            answer.setText(qna.getAnswer());
        }
        return convertView;
    }
}
