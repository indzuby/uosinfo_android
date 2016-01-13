package com.uos.uosinfo.utils;

import com.uos.uosinfo.R;

import java.util.List;
import java.util.Random;

/**
 * Created by user on 2016-01-11.
 */
public class BgUtils {

    public static int[] bg = {R.mipmap.main_bg01,R.mipmap.main_bg02,R.mipmap.main_bg03,R.mipmap.main_bg04,R.mipmap.main_bg05,R.mipmap.main_bg06,R.mipmap.main_bg07,R.mipmap.main_bg08};

    public static int getOtherBg(int res){
        Random r = new Random();
        int n = res;
        do {
            n = r.nextInt(bg.length);
        }while(bg[n] == res);
        return bg[n];
    }
}
