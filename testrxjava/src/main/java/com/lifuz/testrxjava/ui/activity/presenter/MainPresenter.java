package com.lifuz.testrxjava.ui.activity.presenter;

import android.util.Log;

import com.lifuz.testrxjava.MainActivity;
import com.lifuz.testrxjava.api.Test.TestApi;
import com.lifuz.testrxjava.model.test.Test;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * mvp main presenter
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/8/2 20:11
 */
public class MainPresenter {

    private static final String TAG = "MainPresenter";

    private MainActivity activity;

    private TestApi testApi;


    public MainPresenter(MainActivity activity, TestApi testApi) {
        this.activity = activity;
        this.testApi = testApi;
    }

    public void showName(){

        testApi.getTest(1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Test>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(Test test) {

                        Log.e(TAG,"执行成功");

                       activity.showName( "");
                    }
                });
    }
}
