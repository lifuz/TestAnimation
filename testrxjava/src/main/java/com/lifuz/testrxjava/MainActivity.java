package com.lifuz.testrxjava;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.lifuz.testrxjava.ui.activity.component.DaggerMainAcitivityComponent;
import com.lifuz.testrxjava.ui.activity.module.MainActivityModule;
import com.lifuz.testrxjava.ui.activity.presenter.MainPresenter;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @BindView(R.id.iv_show)
    protected ImageView ivShow;

    @BindView(R.id.tv)
    protected TextView tv;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        inject();

        mainPresenter.showName();

//        TestApi testApi = RxService.createAPI(TestApi.class);


//        testTransform();


//        observable.subscribe(observer);

//        observable.subscribe(subscriber)

    }

    private void inject() {
        AppComponent appComponent = ((AppApplication) getApplication()).getAppComponent();

        DaggerMainAcitivityComponent
                .builder()
                .appComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build().inject(this);


    }

    public void showName(String name) {
        tv.setText(name);
    }

    public void testTransform() {

        Observable.just("images/ic_login_qq_icon.png")
                .map(new Func1<String, Bitmap>() {
                    @Override
                    public Bitmap call(String filePath) {
                        try {
                            return BitmapFactory.decodeStream(getAssets().open(filePath));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Bitmap>() {
                    @Override
                    public void call(Bitmap bitmap) {

                        if (bitmap != null) {

//                            ivShow.setImageBitmap(bitmap);
                        }
                    }
                });

    }

    /**
     * 测试使用rxJava 的Scheduler
     */
    public void testScheduler() {

        final int drawableRes = R.mipmap.ic_launcher;

        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {

                Drawable drawable = getDrawable(drawableRes);

                subscriber.onNext(drawable);
                subscriber.onCompleted();

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(Drawable drawable) {

//                        ivShow.setImageDrawable(drawable);

                    }
                });
    }

    //被观察者
    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {

            //事件序列
            subscriber.onNext("Hello");
            subscriber.onNext("Hi");
            subscriber.onNext("Aloha");
            subscriber.onCompleted();
        }
    });

//


    //观察者
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {

            Log.e(TAG, "onCompleted");

        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "onError");
        }

        @Override
        public void onNext(String s) {
            Log.e(TAG, "onNext");
        }
    };

}
