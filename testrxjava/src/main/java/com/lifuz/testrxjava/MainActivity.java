package com.lifuz.testrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        observable.subscribe(observer);

//        observable.subscribe(subscriber)

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


    //观察者
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {

            Log.e(TAG,"onCompleted");

        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG,"onError");
        }

        @Override
        public void onNext(String s) {
            Log.e(TAG,"onNext");
        }
    };

}
