package com.lifuz.map.utils;

import android.content.Context;
import android.widget.Toast;

/**
 *
 * 一些Android 的工具类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/5/8 14:02
 */
public class Utils {

    /**
     * 吐司工具方法
     *
     * @param context
     * @param showStr
     */
    public static void Toast(Context context,String showStr) {
        Toast.makeText(context,showStr,Toast.LENGTH_SHORT).show();
    }

}
