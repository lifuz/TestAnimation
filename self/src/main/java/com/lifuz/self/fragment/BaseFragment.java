package com.lifuz.self.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lifuz.self.R;

import butterknife.ButterKnife;

/**
 *
 * fragment 的基类做一些封装
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/7/12 22:14
 */
public abstract class BaseFragment extends Fragment {



    protected abstract int getLayoutId();

    protected abstract void initView(View view, Bundle savedInstanceState);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(getLayoutId(),container,false);

        ButterKnife.bind(this,view);

        initView(view,savedInstanceState);

        return view;
    }


}
