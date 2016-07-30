package com.lifuz.testrxjava.api.Test;

import com.lifuz.testrxjava.model.test.Test;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 *
 * 测试接口API
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/7/30 11:57
 */
public interface TestApi {

    @POST("seckill/{id}/exposer")
    Observable<Test> getTest(@Path("id") long id);

}
