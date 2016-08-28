package com.lifuz.self.api.mine;

import com.lifuz.self.enums.MineState;
import com.lifuz.self.model.common.SelfResult;
import com.lifuz.self.model.mine.Token;
import com.lifuz.self.model.mine.User;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
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

    /**
     * 用户注册接口
     * @param map
     * @return
     */
    @POST("appUser/register")
    Observable<SelfResult<MineState>> register(@QueryMap Map<String,String> map);

    /**
     * 手机号登录接口
     * @param phone
     * @param passwd
     * @return
     */
    @GET("appUser/{phone}/phoneLogin")
    Observable<SelfResult<Token>> phoneLogin(
            @Path("phone") Long phone, @Query("passwd") String passwd);

    /**
     * qq登录接口
     * @param qqOpenId
     * @return
     */
    @GET("appUser/{qqOpenId}/qqLogin")
    Observable<SelfResult<Token>> qqLogin(@Path("qqOpenId") String qqOpenId);

    /**
     * id号登录接口
     * @param userId
     * @return
     */
    @GET("appUser/{userId}/userId")
    Observable<SelfResult<Token>> userId(@Path("userId") String userId);

}
