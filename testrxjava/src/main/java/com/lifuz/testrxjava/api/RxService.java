package com.lifuz.testrxjava.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * 数据访问的基类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/7/30 11:39
 */
public class RxService {

    //根地址
    private static final String BASEURL = "http://121.41.64.237:8080/seckill/";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RxService() {
    }

    public static <T> T createAPI(Class<T> clazz) {
        return retrofit.create(clazz);
    }
}
