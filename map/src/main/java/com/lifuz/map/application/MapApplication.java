package com.lifuz.map.application;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.lifuz.map.service.LocationService;

/**
 *自定义Application
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/5/8 13:16
 */
public class MapApplication extends Application {

    public LocationService locationService;

    @Override
    public void onCreate() {
        super.onCreate();




        locationService = new LocationService(getApplicationContext());// 注册 SDK 广播监听者

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

    }




}
