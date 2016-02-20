package com.uos.uosinfo.ui;

import android.content.Context;
import android.util.AttributeSet;

import com.kakao.usermgmt.LoginButton;
import com.uos.uosinfo.R;

/**
 * Created by 주현 on 2016-02-18.
 */
public class KaKaoLoginButton extends LoginButton {
    public KaKaoLoginButton(Context context) {
        super(context);
    }

    public KaKaoLoginButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KaKaoLoginButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        inflate(getContext(), R.layout.kako_login_layout, this);
    }
}
