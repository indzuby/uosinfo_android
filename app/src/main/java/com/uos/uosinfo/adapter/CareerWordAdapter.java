package com.uos.uosinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.domain.Word;

import java.util.List;

/**
 * Created by 주현 on 2016-01-28.
 */
public class CareerWordAdapter extends BaseAdapter {

    List<Word> mWords;
    Context mContext;

    View.OnClickListener mListener;

    public CareerWordAdapter(List<Word> mWords, Context mContext, View.OnClickListener listener) {
        this.mWords = mWords;
        this.mContext = mContext;
        this.mListener = listener;
    }

    @Override
    public int getCount() {
        return mWords.size();
    }

    @Override
    public Word getItem(int position) {
        return mWords.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.element_career_word, null);
            convertView = v;
        }
        Word word = getItem(position);
        TextView wordText = (TextView) convertView.findViewById(R.id.word);
        wordText.setTag(word);
        wordText.setText(word.getWord());
        wordText.setOnClickListener(mListener);
        return convertView;
    }
}
