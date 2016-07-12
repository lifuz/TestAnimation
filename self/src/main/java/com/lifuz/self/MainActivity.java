package com.lifuz.self;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.FrameLayout;

import com.lifuz.self.activity.BaseActivity;
import com.lifuz.self.fragment.ContactFragment;
import com.lifuz.self.fragment.KnowledgeFragment;
import com.lifuz.self.fragment.MineFragment;
import com.lifuz.self.fragment.WealthFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private BottomBar bottomBar;

    private FragmentManager fm;

    private KnowledgeFragment knowledgeFragment;
    private WealthFragment wealthFragment;
    private ContactFragment contactFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();

        bottomBar = BottomBar.attach(this, savedInstanceState);

        bottomBar.setItems(R.menu.bottombar_menu);

        bottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorAccent));
        bottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.primary));
        bottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.primary_dark));
        bottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.accent));

//        bottomBar.noNavBarGoodness();

        bottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {

                Log.e(TAG,menuItemId + "onMenuTabSelected");

                FragmentTransaction transaction = fm.beginTransaction();

                hideFragment(transaction);

                switch (menuItemId){
                    case R.id.bottom_one:

                        if (knowledgeFragment == null) {
                            knowledgeFragment = new KnowledgeFragment();
                            transaction.add(R.id.fragment_content,knowledgeFragment);
                        } else {
                            transaction.show(knowledgeFragment);
                        }

                        break;

                    case R.id.bottom_two:

                        if (contactFragment == null) {
                            contactFragment = new ContactFragment();
                            transaction.add(R.id.fragment_content,contactFragment);
                        } else {
                            transaction.show(contactFragment);
                        }

                        break;

                    case R.id.bottom_three:

                        if (wealthFragment == null) {
                            wealthFragment = new WealthFragment();
                            transaction.add(R.id.fragment_content,wealthFragment);
                        } else {
                            transaction.show(wealthFragment);
                        }

                        break;

                    case R.id.bottom_four:

                        if (mineFragment == null) {
                            mineFragment = new MineFragment();
                            transaction.add(R.id.fragment_content,mineFragment);
                        } else {
                            transaction.show(mineFragment);
                        }

                        break;
                }

                transaction.commit();



            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {

                Log.e(TAG,menuItemId + "onMenuTabReSelected");

            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        bottomBar.onSaveInstanceState(outState);
    }

    private void hideFragment(FragmentTransaction transaction){
        if (knowledgeFragment !=null) {
            transaction.hide(knowledgeFragment);
        }

        if (contactFragment != null) {
            transaction.hide(contactFragment);
        }

        if (wealthFragment != null) {
            transaction.hide(wealthFragment);
        }

        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }


}
