package com.lifuz.testrxjava.ui.activity.module;

import com.lifuz.testrxjava.MainActivity;
import com.lifuz.testrxjava.api.Test.TestApi;
import com.lifuz.testrxjava.ui.activity.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 *
 * 数据提供类
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/8/2 20:22
 */
@Module
public class MainActivityModule {

    private MainActivity activity;
//    private Retrofit retrofit;

    public MainActivityModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    public MainActivity provideAcitvity(){
        return  activity;
    }

    @Provides
    public TestApi provideTestApi(Retrofit retrofit) {
        return retrofit.create(TestApi.class);
    }

    @Provides
    public MainPresenter provideMainPresenter(MainActivity activity,TestApi testApi) {

        return new MainPresenter(activity,testApi);

    }


}
