package com.lifuz.testrxjava;

import android.app.Application;

import com.lifuz.testrxjava.api.RxService;

/**
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/8/2 20:45
 */
public class AppApplication extends Application {

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder().rxService(new RxService())
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
