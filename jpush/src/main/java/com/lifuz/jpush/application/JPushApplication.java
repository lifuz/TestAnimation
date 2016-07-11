package com.lifuz.jpush.application;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/5/29 15:51
 */
public class JPushApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JPushInterface.requestPermission(this);
    }
}
