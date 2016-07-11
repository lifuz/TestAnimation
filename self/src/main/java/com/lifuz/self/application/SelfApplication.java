package com.lifuz.self.application;

import android.Manifest;
import android.app.Application;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.baidu.mapapi.SDKInitializer;
import com.lifuz.self.service.LocationService;

/**
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/5/29 14:04
 */
public class SelfApplication extends Application {

    public LocationService locationService;

    private static final int REQUEST_CODE = 110;

    @Override
    public void onCreate() {
        super.onCreate();




        locationService = new LocationService(getApplicationContext());// 注册 SDK 广播监听者

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

    }


}
