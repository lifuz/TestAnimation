package com.lifuz.self.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lifuz.self.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

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

    @BindView(R.id.login_phone_et)
    protected TextInputEditText login_phone_et;

    private Tencent tencent;

    private UserInfo userInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tencent = Tencent.createInstance(getString(R.string.qq_app_id),this);

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

        tencent.login(LoginActivity.this, "all",loginListener);

    }

    @OnClick(R.id.login_btn)
    public void login() {
        Log.e(TAG,"Login is onclick");

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(login_phone_et.getWindowToken(), 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,loginListener);
        }
    }

    public void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                tencent.setAccessToken(token, expires);
                tencent.setOpenId(openId);
            }
        } catch(Exception e) {
        }
    }

    private IUiListener loginListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {

            Log.e(TAG,"qq登录成功");

            Log.e(TAG,o.toString());

            initOpenidAndToken((JSONObject) o);

//            tencent.requestAsync(Constants.GRAPH_SIMPLE_USER_INFO);

            Log.e(TAG,tencent.getQQToken().getOpenId());



            userInfo = new UserInfo(LoginActivity.this, tencent.getQQToken());

            userInfo.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    Log.e(TAG,"获取信息成功");
                    Log.e(TAG,o.toString());
                }

                @Override
                public void onError(UiError uiError) {

                }

                @Override
                public void onCancel() {

                }
            });

        }

        @Override
        public void onError(UiError uiError) {

            Log.e(TAG,"qq登录失败");

        }

        @Override
        public void onCancel() {

            Log.e(TAG,"取消登录");

        }
    };
}
