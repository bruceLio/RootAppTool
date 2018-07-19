package com.xiaolong.rootapptool.utils;

import android.widget.Toast;

import com.xiaolong.rootapptool.MyApplication;

public class T {

    public static void showShort(String text) {
        Toast.makeText(MyApplication.app.getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
}
