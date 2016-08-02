package com.lifuz.testrxjava;

import com.lifuz.testrxjava.api.RxService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/8/2 20:41
 */
@Singleton
@Component(modules = {RxService.class})
public interface AppComponent {

    OkHttpClient getClient();

    Retrofit getRetrofit();

}
