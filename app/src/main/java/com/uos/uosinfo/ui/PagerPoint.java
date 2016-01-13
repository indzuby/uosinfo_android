package com.uos.uosinfo.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.uos.uosinfo.R;
import com.uos.uosinfo.utils.ContextUtils;

/**
 * Created by user on 2016-01-11.
 */
public class PagerPoint {

    public static View getPoint(Context context){
        ImageView imageView = new ImageView(context);
        int padding = ContextUtils.pxFromDp(context, 4);
        imageView.setImageResource(R.drawable.micro_oval_selector);
        imageView.setPadding(padding, padding, padding, padding);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageView;
    }
}
