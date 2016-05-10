package com.lifuz.map;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.lifuz.map.application.MapApplication;
import com.lifuz.map.service.LocationService;
import com.lifuz.map.utils.BaiDuLocationError;
import com.lifuz.map.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 110;
    private static final String TAG = "MainActivity";

    private LocationService locationService = null;

    private PoiSearch poiSearch = null;


    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        locationService = ((MapApplication) getApplication()).locationService;
        locationService.registerListener(locationListener);

        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(poiListener);

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
        iFilter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        iFilter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        mReceiver = new SDKReceiver();
        registerReceiver(mReceiver, iFilter);

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

//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }

                locationService.start();
//                poiSearch.searchInCity(new PoiCitySearchOption().city("上海").keyword("美食").pageNum(6));
            }

        } else {
            locationService.start();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();

        poiSearch.destroy();

        locationService.unregisterListener(locationListener);
        locationService.stop();
    }

    BDLocationListener locationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {

            if (bdLocation.getLocType() == BaiDuLocationError.GPS_LOCATION_RESULT ||
                    bdLocation.getLocType() == BaiDuLocationError.INTERNET_LOCATION_RESULT) {

                Utils.Toast(MainActivity.this, bdLocation.getAddrStr());
                locationService.stop();
                LatLng latLng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());

//                poiSearch.searchNearby((new PoiNearbySearchOption()).location(latLng)
//                        .keyword("美食").pageNum(10).pageCapacity(20).radius(1000));



            } else {
                Utils.Toast(MainActivity.this, "定位失败：" + bdLocation.getLocType());
            }


        }
    };

    OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener() {
        @Override
        public void onGetPoiResult(PoiResult poiResult) {


            Log.e(TAG,poiResult.error + "");

            Log.e(TAG,poiResult.getAllPoi().size() + "");

            for (PoiInfo poiInfo : poiResult.getAllPoi()) {
                Log.e(TAG,poiInfo.address);
            }


        }

        @Override
        public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            Log.e(TAG,poiDetailResult.getAddress() + "lifuz");
            Utils.Toast(MainActivity.this,poiDetailResult.getAddress());
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
//                    poiSearch.searchInCity(new PoiCitySearchOption().city("上海").keyword("美食").pageNum(6));
                    locationService.start();

                } else {

                    Utils.Toast(this, "获取授权失败，定位不能进行");

                }

                break;
        }

    }

    /**
     * 构造广播监听类，监听 SDK key 验证以及网络异常广播
     */
    public class SDKReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            String s = intent.getAction();
            Log.d("TAG", "action: " + s);
            if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
//                text.setText("key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");

                Log.e("tag" , "key 验证出错! 请在 AndroidManifest.xml 文件中检查 key 设置");
            } else if (s.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK)) {
//                text.setText("key 验证成功! 功能可以正常使用");
                Log.e("tag" , "key 验证成功! 功能可以正常使用");
//                poiSearch.searchInCity(new PoiCitySearchOption().city("上海").keyword("美食").pageNum(20).pageCapacity(20));

//                text.setTextColor(Color.YELLOW);
            }
            else if (s.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
//                text.setText("网络出错");
                Log.e("tag" , "网络出错");
            }
        }
    }

}
