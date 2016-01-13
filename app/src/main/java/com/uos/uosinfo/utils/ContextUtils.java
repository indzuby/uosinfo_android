package com.uos.uosinfo.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by user on 2016-01-11.
 */
public class ContextUtils {
    public static int pxFromDp(Context context, int dp) {
        return Math.round(dp * context.getResources().getDisplayMetrics().density);
    }
}
