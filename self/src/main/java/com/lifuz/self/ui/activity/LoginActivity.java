package com.lifuz.self.ui.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lifuz.self.R;
import com.lifuz.self.application.AppComponent;
import com.lifuz.self.application.SelfApplication;
import com.lifuz.self.model.common.SelfResult;
import com.lifuz.self.model.mine.Token;
import com.lifuz.self.ui.activity.component.DaggerLoginComponent;
import com.lifuz.self.ui.activity.module.LoginModule;
import com.lifuz.self.ui.activity.presenter.LoginPresenter;
import com.lifuz.self.ui.widget.PasswdEditText;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页面
 * <p/>
 * 作者：李富
 * 邮箱：lifuzz@163.com
 * 时间：2016/7/14 11:51
 */
public class LoginActivity extends BaseActivity {


    private static final String TAG = "LoginActivity";

    @Inject
    LoginPresenter loginPresenter;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.center_title)
    protected TextView centerTitle;

    @BindView(R.id.qq_login_btn)
    protected ImageButton qq_login_btn;

    @BindView(R.id.login_phone_et)
    protected TextInputEditText login_phone_et;

    @BindView(R.id.login_passwd_et)
    protected PasswdEditText login_passwd_et;

    private boolean passwdFlag = true;

    private Tencent tencent;

    private UserInfo userInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        inject();

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        tencent = Tencent.createInstance(getString(R.string.qq_app_id), this);

        initView();


//        loginPresenter.phoneLogin(13013874964L,"8");
//        loginPresenter.qqLogin("1234");

    }

    public void show(){
        Toast.makeText(this,"xianshi",Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        qq_login_btn.setFocusable(true);
        qq_login_btn.requestFocus();

        qq_login_btn.setFocusableInTouchMode(true);
        qq_login_btn.requestFocusFromTouch();

//        login_passwd_et.setError("密码不能为空");

//        login_passwd_et.setch

        login_passwd_et.setOnDrawableRightListener(new PasswdEditText.OnDrawableRightListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDrawableRightClick(View view) {
                Drawable rightDrawable = null;
                login_passwd_et.setSelected(false);
                if (passwdFlag) {

                    rightDrawable = getDrawable(R.mipmap.ic_action_visibility);

                    //设置明文密码
                    login_passwd_et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());


                    passwdFlag = false;

                } else {


                    rightDrawable = getDrawable(R.mipmap.ic_action_visibility_off);

                    //设置密文密码
                    login_passwd_et.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    passwdFlag = true;

                }

                login_passwd_et.postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = login_passwd_et.getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());

                }

                login_passwd_et.setCompoundDrawablesWithIntrinsicBounds(null, null, rightDrawable, null);


            }
        });


        centerTitle.setText("登录");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(0F);
        }
    }

    private void inject() {

        AppComponent appComponent = ((SelfApplication)getApplication()).getAppComponent();

        DaggerLoginComponent.builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))
                .build().inject(this);

    }

    public void qqlogin(SelfResult<Token> tokenSelfResult){



        if (tokenSelfResult.isSuccess()){

        } else {
            if (tokenSelfResult.equals("此用户尚未注册")){

                userInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        Log.e(TAG, "获取信息成功");
//                        Log.e(TAG, o.toString());

                        Gson gson = new Gson();

                    }

                    @Override
                    public void onError(UiError uiError) {

                    }

                    @Override
                    public void onCancel() {

                    }
                });

            }
        }

    }

    /**
     * qq登录
     */
    @OnClick(R.id.qq_login_btn)
    public void qqLoginBtn() {

        Log.e(TAG, "qq_login_btn is onClick");

        tencent.login(LoginActivity.this, "all", loginListener);

    }

    @OnClick(R.id.login_btn)
    public void login() {
        Log.e(TAG, "Login is onclick");

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(login_phone_et.getWindowToken(), 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
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
        } catch (Exception e) {
        }
    }

    private IUiListener loginListener = new IUiListener() {
        @Override
        public void onComplete(Object o) {

            Log.e(TAG, "qq登录成功");

            Log.e(TAG, o.toString());

            initOpenidAndToken((JSONObject) o);

//            tencent.requestAsync(Constants.GRAPH_SIMPLE_USER_INFO);

            Log.e(TAG, tencent.getQQToken().getOpenId());


//            userInfo = new UserInfo(LoginActivity.this, tencent.getQQToken());

            loginPresenter.qqLogin(tencent.getQQToken().getOpenId());



        }

        @Override
        public void onError(UiError uiError) {

            Log.e(TAG, "qq登录失败");

        }

        @Override
        public void onCancel() {

            Log.e(TAG, "取消登录");

        }
    };
}
