package com.xiaolong.rootapptool;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;

public class DaemonService extends Service {
    public static final int NOTICE_ID = 100;
    private BroadcastReceiver receiver;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void start(Context context) {
        // 添加try
        // catch，oppo的部分机型因为电源管理程序，一旦认为某个程序是耗电应用，并且该程序在后台启动service，则报出空指针错误
        try {
            Intent intent = new Intent(context, DaemonService.class);
            context.startService(intent);
        } catch (Exception e) {
        }

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("xiaolong", "serviceCreate");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.e("Xiaolong", new Date().toString());
            }
        };
        registerReceiver(receiver, new IntentFilter(Intent.ACTION_TIME_TICK));
        //如果API大于18，需要弹出一个可见通知
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentTitle("KeepAppAlive");
            builder.setContentText("DaemonService is runing...");
            startForeground(NOTICE_ID, builder.build());
            // 如果觉得常驻通知栏体验不好
            // 可以通过启动CancelNoticeService，将通知移除，oom_adj值不变
//            Intent intent = new Intent(this, CancelNoticeService.class);
//            startService(intent);
        } else {
            startForeground(NOTICE_ID, new Notification());
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 如果Service被终止
        // 当资源允许情况下，重启service
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("xiaolong", "serviceDestory");
        // 如果Service被杀死，干掉通知
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            NotificationManager mManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mManager.cancel(NOTICE_ID);
        }
        unregisterReceiver(receiver);
        // 重启自己
        Intent intent = new Intent(getApplicationContext(), DaemonService.class);
        startService(intent);
    }
}
