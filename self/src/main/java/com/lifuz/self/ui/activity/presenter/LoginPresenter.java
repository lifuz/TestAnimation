package com.lifuz.self.ui.activity.presenter;

import android.util.Log;

import com.lifuz.self.api.mine.UserApi;
import com.lifuz.self.enums.MineState;
import com.lifuz.self.model.common.SelfResult;
import com.lifuz.self.model.mine.Token;
import com.lifuz.self.model.mine.User;
import com.lifuz.self.ui.activity.LoginActivity;

import java.util.Map;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 * 登录
 *
 * mvp presenter
 *
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/8/6 14:39
 */
public class LoginPresenter {


    private static final String TAG = "LoginPresenter";

    private LoginActivity activity;

    private UserApi userApi;

    public LoginPresenter(LoginActivity activity, UserApi userApi) {
        this.activity = activity;
        this.userApi = userApi;
    }

    public void phoneLogin(Long phone,String passwd){

        userApi.phoneLogin(phone,passwd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelfResult<Token>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,e.getMessage());

                    }

                    @Override
                    public void onNext(SelfResult<Token> tokenSelfResult) {

                        Log.e(TAG,tokenSelfResult.toString());
                        activity.show();

                    }
                });
    }

    public void register(Map<String,String> map) {
        userApi.register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelfResult<MineState>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(SelfResult<MineState> mineStateSelfResult) {
                        Log.e(TAG,mineStateSelfResult.toString());

                        activity.register(mineStateSelfResult);

                    }
                });
    }

    public void qqLogin(String qqOpenId){

        userApi.qqLogin(qqOpenId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SelfResult<Token>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,e.getMessage());
                    }

                    @Override
                    public void onNext(SelfResult<Token> tokenSelfResult) {

                        Log.e(TAG,tokenSelfResult.toString());
                        activity.qqlogin(tokenSelfResult);

                    }
                });

    }
}
