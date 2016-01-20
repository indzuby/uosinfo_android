package com.uos.uosinfo.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.uos.uosinfo.R;
import com.uos.uosinfo.domain.Library;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.List;

/**
 * Created by user on 2016-01-07.
 */
public class LibraryAdapter extends BaseAdapter{

    Context mContext;
    List<Library> mList;

    public LibraryAdapter(Context mContext, List<Library> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Library getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            View v = LayoutInflater.from(mContext).inflate(R.layout.element_library, null);
            convertView = v;
        }
        ImageView newIcon = (ImageView) convertView.findViewById(R.id.new_icon);
        TextView title = (TextView) convertView.findViewById(R.id.library_title);
        ImageButton download = (ImageButton) convertView.findViewById(R.id.download);

        final Library library=  getItem(position);

        newIcon.setVisibility(View.GONE);
        if(library.isNew())
            newIcon.setVisibility(View.VISIBLE);
        title.setText(library.getTitle());
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(library.getUrl());
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(launchBrowser);
            }
        });
        return convertView;
    }
}
