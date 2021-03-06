package com.neusoft.woaccept.customview;


import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import com.neusoft.woaccept.R;


/**
 * @author NST
 */
public class ClearEditText extends EditText implements OnFocusChangeListener {
    private Context context;
    private Drawable mClearDrawable;

    public ClearEditText(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    private void init() {
        //获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
        mClearDrawable = getCompoundDrawables()[2];
        if (mClearDrawable == null) {
            mClearDrawable = context.getResources().getDrawable(R.drawable.ic_delete);
        }
        addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                setDrawable();
            }
        });
        setDrawable();
    }


    private void setDrawable() {
        if (length() == 0) {
            setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], getCompoundDrawables()[0], null, getCompoundDrawables()[0]);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], getCompoundDrawables()[0], mClearDrawable, getCompoundDrawables()[0]);
        }
    }

    /**
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mClearDrawable != null && event.getAction() == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            boolean isInnerWidth = (x > (getWidth() - getTotalPaddingRight()))
                    && (x < (getWidth() - getPaddingRight()));
            Rect rect = mClearDrawable.getBounds();
            int height = rect.height();
            int y = (int) event.getY();
            int distance = (getHeight() - height) / 2;
            boolean isInnerHeight = (y > distance) && (y < (distance + height));
            if (isInnerWidth && isInnerHeight) {
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            setDrawable();
        } else {
            setCompoundDrawablesWithIntrinsicBounds(getCompoundDrawables()[0], getCompoundDrawables()[0], null, getCompoundDrawables()[0]);
        }
    }
}
