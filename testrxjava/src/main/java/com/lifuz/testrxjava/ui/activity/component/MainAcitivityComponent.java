package com.lifuz.testrxjava.ui.activity.component;

import com.lifuz.testrxjava.ActivityScope;
import com.lifuz.testrxjava.AppComponent;
import com.lifuz.testrxjava.MainActivity;
import com.lifuz.testrxjava.ui.activity.module.MainActivityModule;

import dagger.Component;

/**
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/8/2 20:50
 */
@ActivityScope
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface MainAcitivityComponent {

    void inject(MainActivity mainActivity);
}
