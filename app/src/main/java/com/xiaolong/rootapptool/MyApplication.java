package com.xiaolong.rootapptool;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Process;

import com.xiaolong.rootapptool.services.DaemonService;

import java.util.List;

/**
 * Created by xiaolong on 2018/7/18.
 */
public class MyApplication extends Application {
   public static MyApplication app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        if (isMainProcess()) {
            DaemonService.start(getApplicationContext());
        }

    }

    private boolean isMainProcess() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
