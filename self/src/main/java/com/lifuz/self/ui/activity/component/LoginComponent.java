package com.lifuz.self.ui.activity.component;

import com.lifuz.self.application.AppComponent;
import com.lifuz.self.ui.ActivityScope;
import com.lifuz.self.ui.activity.LoginActivity;
import com.lifuz.self.ui.activity.module.LoginModule;

import dagger.Component;

/**
 *
 * 登录页面
 *
 * mvp component
 *
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/8/6 15:12
 */
@ActivityScope
@Component(modules = LoginModule.class ,dependencies = AppComponent.class)
public interface LoginComponent {

    void inject(LoginActivity activity);

}
