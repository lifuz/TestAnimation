package com.lifuz.self.service;

import android.content.Context;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

/**
 * 定位的服务类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/5/8 12:30
 */
public class LocationService {

    private LocationClient client = null;
    private LocationClientOption option, diyOption;
    private Object objLock = new Object();

    public LocationService(Context context) {
        synchronized (objLock) {

            if (client == null) {
                client = new LocationClient(context);
                client.setLocOption(getDefaultLocationClientOption());
            }
        }
    }

    /**
     * 注册定位结果监听器
     * @param listener 结果监听器
     * @return
     */
    public boolean registerListener(BDLocationListener listener) {
        boolean isSuccess = false;

        if (listener != null) {
            client.registerLocationListener(listener);
            isSuccess = true;
        }

        return isSuccess;
    }

    /**
     * 解除定位结果监听器
     * @param listener 结果监听器
     * @return
     */
    public boolean unregisterListener(BDLocationListener listener) {
        boolean isSuccess = false;

        if (listener != null){
            client.unRegisterLocationListener(listener);
            isSuccess = true;
        }

        return  isSuccess;
    }

    /**
     * 使用自己定义的定位配置
     * @param diyOption
     * @return
     */
    public boolean setLocationClientOption(LocationClientOption diyOption) {

        boolean isSucess = false;

        if (diyOption != null) {
            if (client.isStarted()) {
                client.stop();
            }

            this.diyOption = diyOption;
            isSucess = true;

        }

        return  isSucess;
    }

    /**
     * 获取定位配置信息
     * @return
     */
    public LocationClientOption getOption() {
        return diyOption;
    }

    /**
     * 设置默认定位配置信息
     *
     * @return
     */
    public LocationClientOption getDefaultLocationClientOption() {
        if (option == null) {
            option = new LocationClientOption();
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
            int span = 1000;
            option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
            option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            option.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
            option.setOpenGps(true);//可选，默认false,设置是否使用gps
            option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
            option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
            option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        }

        return option;
    }

    /**
     * 开始定位
     */
    public void start() {
        synchronized (objLock) {
            if (client != null && !client.isStarted()) {
                client.start();
            }
        }
    }

    /**
     * 停止定位
     */
    public void stop() {
        synchronized (objLock) {
            if (client != null && client.isStarted()) {
                client.stop();
            }
        }
    }
}
