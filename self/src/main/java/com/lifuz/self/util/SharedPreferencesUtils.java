package com.lifuz.self.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.lifuz.self.model.mine.Token;

import java.util.Map;

/**
 * share文件的工具类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/8/13 10:33
 */
public class SharedPreferencesUtils {

    /**
     * 保存数据到share文件中
     *
     * @param context
     * @param map
     * @return
     */
    public static boolean saveTakon(Context context, Map<String, String> map) {

        boolean success = false;

        SharedPreferences share = context.getSharedPreferences("self", Activity.MODE_PRIVATE);

        SharedPreferences.Editor editor = share.edit();


        for (String key : map.keySet()) {

            editor.putString(key, map.get(key));
        }

        editor.commit();
        success = true;

        return success;

    }

    /**
     * 获取 token 信息
     *
     * @param context
     * @return
     */
    public static Token getToken(Context context) {
        Token token = null;

        SharedPreferences share = context.getSharedPreferences("self", Activity.MODE_PRIVATE);

        String tokenStr = share.getString("token", "");

        if (!"".equals(tokenStr)) {

            Gson gson = new Gson();

            token = gson.fromJson(tokenStr, Token.class);

        }

        return token;
    }

}
