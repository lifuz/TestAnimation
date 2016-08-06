package com.lifuz.self.ui.activity.module;

import com.lifuz.self.api.mine.UserApi;
import com.lifuz.self.ui.activity.LoginActivity;
import com.lifuz.self.ui.activity.presenter.LoginPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 *
 * 登录页面
 *
 * mvp module
 *
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/8/6 15:02
 */
@Module
public class LoginModule {

    private LoginActivity activity;

    public LoginModule(LoginActivity activity) {
        this.activity = activity;
    }

    @Provides
    public LoginActivity provideActivity(){
        return activity;
    }

    @Provides
    public UserApi provideUserApi(Retrofit retrofit){

        return retrofit.create(UserApi.class);

    }

    @Provides
    public LoginPresenter provideLoginPresenter(UserApi userApi){

        return new LoginPresenter(activity,userApi);

    }


}
