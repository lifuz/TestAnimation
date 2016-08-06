package com.lifuz.self.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/7/24 11:01
 */
public class PasswdEditText extends TextInputEditText {

    private final int DRAWABLE_RIGHT = 2;

    private OnDrawableRightListener mRightListener;

    public void setOnDrawableRightListener(OnDrawableRightListener mRightListener) {
        this.mRightListener = mRightListener;
    }


    public PasswdEditText(Context context) {
        super(context);
    }

    public PasswdEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PasswdEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:

                if (mRightListener != null) {

                    Drawable drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT];
                    if (drawableRight != null && event.getRawX() >= (getRight() - drawableRight.getBounds().width())) {
                        mRightListener.onDrawableRightClick(this);
                        return true;
                    }
                }


                break;
        }

        return super.onTouchEvent(event);
    }

    public interface OnDrawableRightListener {
        void onDrawableRightClick(View view);
    }
}

