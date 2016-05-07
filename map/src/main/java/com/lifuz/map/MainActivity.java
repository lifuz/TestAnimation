package com.lifuz.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.lifuz.map.listener.MyLocationListener;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 110;
    private static final String TAG = "MainActivity";

    private LocationClient locationClient = null;
    private BDLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        locationClient = new LocationClient(getApplicationContext());
        locationClient.registerLocationListener(myListener);

        initLocation();

        //安卓6.0开始某些权限需要动态获取，以下就是动态获取授权的方法
        if (Build.VERSION.SDK_INT >= 23) {

            //判断权限是否已经授权
            int checkACCESSCOARSELOCATION = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION);
            int checkACCESSFINELOCATION = ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION);

            String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

            //如果没有授权，则调用授权方法
            if (checkACCESSCOARSELOCATION != PackageManager.PERMISSION_GRANTED
                    || checkACCESSFINELOCATION != PackageManager.PERMISSION_GRANTED) {

                //请求权限
                ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE);
                return;

            } else {
                Log.e(TAG, "已授权");

                locationClient.start();
            }

//            if ()


        } else {
            locationClient.start();
        }
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
//        option.setOpenGps(true);
        option.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        locationClient.setLocOption(option);

    }

    /**
     * 授权结果返回方法
     * @param requestCode  授权请求的返回吗
     * @param permissions 请求那些权限
     * @param grantResults 每个权限的授权结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //根据授权的返回码，确定授权的调用者
        switch (requestCode) {
            case REQUEST_CODE:

                Log.e(TAG, grantResults[0] + "  " + grantResults[1] + "  " + PackageManager.PERMISSION_GRANTED);

                break;
        }

    }
}
