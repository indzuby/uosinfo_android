package com.uos.uosinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.domain.Notice;

import java.util.List;

/**
 * Created by 주현 on 2016-02-16.
 */
public class NoticeAdapter extends BaseExpandableListAdapter{
    Context mContext;
    List<Notice> mNotices;

    public NoticeAdapter(Context mContext, List<Notice> mNotices) {
        this.mContext = mContext;
        this.mNotices = mNotices;
    }

    @Override
    public int getGroupCount() {
        return mNotices.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Notice getGroup(int groupPosition) {
        return mNotices.get(groupPosition);
    }

    @Override
    public Notice getChild(int groupPosition, int childPosition) {
        return getGroup(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView==null) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.element_notice_group, null);
            convertView = v;
        }
        ImageView newIcon = (ImageView) convertView.findViewById(R.id.new_icon);
        TextView title = (TextView) convertView.findViewById(R.id.notice_title);
        ImageView openButton = (ImageView) convertView.findViewById(R.id.content_open);
        View line = convertView.findViewById(R.id.line);
        final Notice notice =  getGroup(groupPosition);

        newIcon.setVisibility(View.GONE);
        if(notice.isNew())
            newIcon.setVisibility(View.VISIBLE);
        title.setText(notice.getTitle());
        if(isExpanded) {
            line.setVisibility(View.INVISIBLE);
            openButton.setImageDrawable(mContext.getDrawable(R.mipmap.notice_close));
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                convertView.setBackgroundColor(mContext.getColor(R.color.notice_open_background));
            else
                convertView.setBackgroundColor(mContext.getResources().getColor(R.color.notice_open_background));
            convertView.invalidate();
        }else {
            line.setVisibility(View.VISIBLE);
            openButton.setImageDrawable(mContext.getDrawable(R.mipmap.notice_open));
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                convertView.setBackgroundColor(mContext.getColor(R.color.library_background));
            else
                convertView.setBackgroundColor(mContext.getResources().getColor(R.color.library_background));
            convertView.invalidate();
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView==null) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.element_notice_child, null);
            convertView = v;
        }
        TextView contents = (TextView) convertView.findViewById(R.id.notice_contents);
        ImageButton openBrowser = (ImageButton) convertView.findViewById(R.id.open_browser);

        final Notice notice =  getGroup(groupPosition);

        contents.setText(notice.getContent());
        openBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(notice.getUrl());
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(launchBrowser);
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
