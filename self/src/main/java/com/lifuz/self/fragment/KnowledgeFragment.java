package com.lifuz.self.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lifuz.self.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * 知识模块的fragment
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/7/12 21:34
 */
public class KnowledgeFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;

    @BindView(R.id.center_title)
    protected TextView centerTitle;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

        centerTitle.setText("知识");

    }


}
