package com.xiaolong.rootapptool.utils;

import android.text.TextUtils;
import android.util.Log;

import com.xiaolong.rootapptool.BuildConfig;

public class L {


    public static final String TAG = "rootTool";

    private static boolean DEBUG = true;

    static {
        DEBUG = BuildConfig.DEBUG;
    }

    public static void e(String msg) {
        if (DEBUG && !TextUtils.isEmpty(msg))
            Log.e(TAG, msg);
    }


}
