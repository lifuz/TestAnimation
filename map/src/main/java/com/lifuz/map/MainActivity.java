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

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.lifuz.map.application.MapApplication;
import com.lifuz.map.listener.MyLocationListener;
import com.lifuz.map.service.LocationService;
import com.lifuz.map.utils.BaiDuLocationError;
import com.lifuz.map.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 110;
    private static final String TAG = "MainActivity";

    private LocationService locationService = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
//        //注意该方法要再setContentView方法之前实现
//        SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        locationService = ((MapApplication) getApplication()).locationService;
        locationService.registerListener(locationListener);

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

                locationService.start();
            }

        } else {
            locationService.start();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();

        locationService.unregisterListener(locationListener);
        locationService.stop();
    }

    BDLocationListener locationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            if (bdLocation.getLocType() == BaiDuLocationError.GPS_LOCATION_RESULT ||
                    bdLocation.getLocType() == BaiDuLocationError.INTERNET_LOCATION_RESULT) {

                Utils.Toast(MainActivity.this, bdLocation.getAddrStr());

            } else {
                Utils.Toast(MainActivity.this, "定位失败：" + bdLocation.getLocType());
            }


        }
    };

    /**
     * 授权结果返回方法
     *
     * @param requestCode  授权请求的返回吗
     * @param permissions  请求那些权限
     * @param grantResults 每个权限的授权结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //根据授权的返回码，确定授权的调用者
        switch (requestCode) {
            case REQUEST_CODE:

                Log.e(TAG, grantResults[0] + "  " + grantResults[1] + "  " + PackageManager.PERMISSION_GRANTED);

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    locationService.start();

                } else {

                    Utils.Toast(this, "获取授权失败，定位不能进行");

                }

                break;
        }

    }

}
