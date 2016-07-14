package com.lifuz.self.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.lifuz.self.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * 登录页面
 *
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/7/14 11:51
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.center_title)
    protected TextView centerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        centerTitle.setText("登录");

    }
}
