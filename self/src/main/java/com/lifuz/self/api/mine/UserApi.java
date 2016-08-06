package com.lifuz.self.api.mine;

import com.lifuz.self.enums.MineState;
import com.lifuz.self.model.common.SelfResult;
import com.lifuz.self.model.mine.Token;
import com.lifuz.self.model.mine.User;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 用户相关信息
 * <p/>
 * api 请求
 * <p/>
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/8/6 13:50
 */
public interface UserApi {

    @POST("appUser/register")
    Observable<SelfResult<MineState>> register(@Body User user);

    @GET("appUser/{phone}/phoneLogin")
    Observable<SelfResult<Token>> phoneLogin(
            @Path("phone") Long phone, @Query("passwd") String passwd);

    @GET("appUser/{qqOpenId}/qqLogin")
    Observable<SelfResult<Token>> qqLogin(@Path("qqOpenId") String qqOpenId);

}
