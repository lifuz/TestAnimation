package com.lifuz.self.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lifuz.self.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *
 * 登录页面
 *
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/7/14 11:51
 */
public class LoginActivity extends BaseActivity {



    private static final String TAG = "LoginActivity";

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.center_title)
    protected TextView centerTitle;

    @BindView(R.id.qq_login_btn)
    protected ImageButton qq_login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        qq_login_btn.setFocusable(true);
        qq_login_btn.requestFocus();

        qq_login_btn.setFocusableInTouchMode(true);
        qq_login_btn.requestFocusFromTouch();


        centerTitle.setText("登录");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(0F);
        }


    }

    /**
     * qq登录
     */
    @OnClick(R.id.qq_login_btn)
    public void qqLogin() {

        Log.e(TAG,"qq_login_btn is onClick");

    }

    @OnClick(R.id.login_btn)
    public void login() {
        Log.e(TAG,"Login is onclick");

        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        if(im.isActive()) {
            im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }



    }
}
