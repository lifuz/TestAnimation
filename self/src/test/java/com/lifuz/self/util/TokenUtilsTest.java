package com.lifuz.self.util;

import android.provider.Settings;
import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/8/28 12:02
 */
public class TokenUtilsTest {

    private static final String TAG = "TokenUtilsTest";

    @Test
    public void testValidToken() throws Exception {

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiIxIiwiZXhwIjoxNDcyMzU3NDMxLCJpYXQiOjE0NzIzNTY4MzF9.72lPBAtGAC8vUwGextopwrLPslxtXy3qljCy_W-yWpk";
        System.out.println(TokenUtils.validToken(token));

    }
}