package com.lifuz.map.application;

import android.app.Application;

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

        locationService = new LocationService(getApplicationContext());

    }


}
