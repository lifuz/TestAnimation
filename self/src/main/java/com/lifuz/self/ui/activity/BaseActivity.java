package com.lifuz.self.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * activity 基类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/7/11 21:42
 */
public class BaseActivity extends AppCompatActivity {


    private static final int REQUEST_CODE = 110;


    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

            }


        }

    }

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

                break;
        }

    }
}
