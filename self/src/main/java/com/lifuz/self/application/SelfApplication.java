package com.lifuz.self.application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.lifuz.self.service.LocationService;

/**
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/5/29 14:04
 */
public class SelfApplication extends Application {

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
