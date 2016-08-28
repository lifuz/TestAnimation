package com.lifuz.self.application;

import com.lifuz.self.api.APIService;
import com.lifuz.self.api.mine.UserApi;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 *  总component组件
 *
 *  component 部分
 *
 *  提供了网络请求的封装
 *
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/8/6 13:43
 */
@Singleton
@Component ( modules = {APIService.class})
public interface AppComponent {

    OkHttpClient getClient();

    Retrofit getRetrofit();

    UserApi getUserApi();

}
