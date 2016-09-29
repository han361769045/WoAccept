package com.neusoft.woaccept.customview;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.neusoft.woaccept.R;

import java.util.ArrayList;
import java.util.List;

import static android.view.Gravity.CENTER;

/**
 * Created by LeoLu on 2016/9/14.
 */

public class PaymentSegment extends LinearLayout {

    static final String TAG = "PaymentSegment";

    private int mRowCount, mColumnCount;

    private List<TextView> mItemTextViews;

    private List<LinearLayout> mLinearLayouts;

    private CharSequence[] mItemText;

    private String mItemTextSuffix;

    private Drawable mDrawable;

    private int mDrawableIds;

    private ColorStateList mItemTextColor;

    private boolean mIsItemEndEditText;

    private LinearLayout mItemLastLinearLayout;

    private TextView mSuffixTextView;

    private EditText mLastEditText;

    private InputMethodManager inputMethodManager;

    private OnItemClickListener onItemClickListener;

    private boolean isNum;

    private String selectValue;

    public PaymentSegment(Context context) {
        this(context, null);
    }

    public PaymentSegment(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaymentSegment(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PaymentSegment, defStyleAttr, 0);
//        mRowCount = a.getInt(R.styleable.PaymentSegment_mRowCount, 1);
        mColumnCount = a.getInt(R.styleable.PaymentSegment_mColumnCount, 1);
        mDrawable = a.getDrawable(R.styleable.PaymentSegment_mItemBackground);
        mDrawableIds = a.getResourceId(R.styleable.PaymentSegment_mItemBackground, 0);
        mItemTextColor = a.getColorStateList(R.styleable.PaymentSegment_mItemTextColor);
        mItemText = a.getTextArray(R.styleable.PaymentSegment_mItemText);
        mItemTextSuffix = a.getString(R.styleable.PaymentSegment_mItemTextSuffix);
        mIsItemEndEditText = a.getBoolean(R.styleable.PaymentSegment_mIsItemEndEditText, false);
        initView();
        a.recycle();
    }

    private void initView() {
        if (mItemText == null) {
            throw new RuntimeException(TAG + "--------mItemText is required! ");
        }
        inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        mRowCount = mItemText.length % mColumnCount == 0 ? mItemText.length / mColumnCount : mItemText.length / mColumnCount + 1;
        mLinearLayouts = new ArrayList<>(mRowCount);
        mItemTextViews = new ArrayList<>(mItemText.length);
        ensureView();
        for (TextView textView : mItemTextViews) {
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeStatue(v);
                }
            });
        }
        if (mLastEditText != null) {
            mLastEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        v.setFocusable(true);
                        v.requestFocus();
                        ((EditText) v).setText(" ");
                        showInputMethod(v);
                        //修改当前选中的状态
                        for (TextView txt : mItemTextViews) {
                            if (txt == v) {
                                txt.setSelected(true);
                            } else {
                                txt.setSelected(false);
                            }
                        }
                        selectValue = "0.00";
                        isNum = false;
                    } else if (v.isSelected()) {
                        selectValue = ((EditText) v).getText().toString();
                        isNum = false;
                    } else {
                        ((EditText) v).setText("");
                        ((EditText) v).setHint("自定义");
                    }
                    mItemLastLinearLayout.setSelected(hasFocus || v.isSelected());
                    mSuffixTextView.setVisibility(hasFocus || v.isSelected() ? View.VISIBLE : View.GONE);
                }
            });

            mLastEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String temp = s.toString().trim();
                    if (temp.length() == 0) {
                        isNum = false;
                    } else if (temp.length() == 1 && ("".equals(temp) || ".".equals(temp))) {
                        isNum = false;
                    } else if (Double.valueOf(s.toString()) > 0) {
                        isNum = true;
                    }
                    selectValue = temp;
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClickListener(isNum, selectValue);
                    }
                }
            });

            mLastEditText.setFilters(new InputFilter[]{
                    new InputFilter() {
                        @Override
                        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                            if ("".equals(source.toString())) {
                                return null;
                            }
                            String dValue = dest.toString();
                            String[] splitArray = dValue.split("\\.");
                            if (splitArray.length > 1) {
                                String dotValue = splitArray[1].trim();
                                int diff = dotValue.length() + 1 - 2;
                                if (diff > 0) {
                                    return source.subSequence(start, end - diff);
                                }
                            }
                            return null;
                        }
                    }
            });
        }
    }

    //修改选中状态
    private void changeStatue(View view) {
        //为了防止失去焦点selectValue被清空
        String tempValue = "";
        if (view.isSelected()) {
            return;
        }
        //如果点击的是不EditText
        if (!(view instanceof EditText) && mSuffixTextView.isShown()) {
            mLastEditText.setText("");
            mSuffixTextView.setVisibility(View.GONE);
        }
        mItemLastLinearLayout.setSelected(false);
        //修改当前选中的状态
        int temp = 0;
        for (TextView txt : mItemTextViews) {
            txt.clearFocus();
            if (txt == view) {
                txt.setSelected(true);
                selectValue = tempValue = mItemText[temp].toString();
            } else {
                txt.setSelected(false);
            }
            temp++;
        }
        //如果点击的是不EditText
        if (!(view instanceof EditText)) {
            closeInputMethod(null);
            isNum = true;
            if (onItemClickListener != null) {
                selectValue = tempValue;
                onItemClickListener.onItemClickListener(true, "".equals(selectValue.trim()) ? "0.00" : selectValue);
            }
        } else {
            selectValue = "0.00";
            isNum = false;
        }
    }

    private void ensureView() {
        int index = 0;
        for (int i = 0; i < mRowCount; i++) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setClickable(false);
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
            linearLayout.setGravity(CENTER);
            linearLayout.setOrientation(HORIZONTAL);
            linearLayout.setLayoutParams(layoutParams);
            for (int j = 0; j < mColumnCount; j++) {
                TextView textView;
                if (index >= mItemText.length) {
                    break;
                } else if (index == mItemText.length - 1 && mIsItemEndEditText) {
                    mItemLastLinearLayout = createLinearLayout();
                    mLastEditText = createEditText(mItemText[index].toString());
                    mItemLastLinearLayout.addView(mLastEditText);
                    mItemLastLinearLayout.addView(mSuffixTextView = createSuffixTextView());
                    linearLayout.addView(mItemLastLinearLayout);
                    textView = mLastEditText;
                } else {
                    textView = createTextView(mItemText[index].toString());
                    linearLayout.addView(textView);
                }
                mItemTextViews.add(textView);
                index++;
            }
            mLinearLayouts.add(linearLayout);
            this.addView(linearLayout);
        }
    }

    private TextView createTextView(String mText) {
        TextView textView = new TextView(getContext());
        textView.setSingleLine();
        textView.setGravity(CENTER);
        textView.setClickable(true);
        LayoutParams layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;
        if (mDrawableIds != 0) {
            textView.setBackgroundResource(mDrawableIds);
        }
        textView.setPadding(pxFromDp(15f), pxFromDp(15f), pxFromDp(15f), pxFromDp(15f));
        if (mItemTextColor != null) {
            textView.setTextColor(mItemTextColor);
        }
        if (mText != null) {
            textView.setText(mText.concat(mItemTextSuffix));
        }
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    private EditText createEditText(String hint) {
        EditText editText = new EditText(getContext());
        editText.setSingleLine();
        editText.setGravity(Gravity.END);
        editText.setClickable(true);
        editText.setInputType(EditorInfo.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
        editText.setMaxLines(1);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
        editText.setTextSize(14);
        editText.setBackgroundColor(Color.WHITE);
        if (mItemTextColor != null) {
            editText.setTextColor(mItemTextColor);
        }
        editText.setHint(hint);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        editText.setLayoutParams(layoutParams);
        return editText;
    }

    private TextView createSuffixTextView() {
        TextView textView = new TextView(getContext());
        textView.setSingleLine();
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView.setLayoutParams(layoutParams);
        textView.setVisibility(GONE);
        textView.setTextSize(14);
        textView.setTextColor(Color.argb(255, 253, 120, 0));
        textView.setText(mItemTextSuffix);
        return textView;
    }

    private LinearLayout createLinearLayout() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(HORIZONTAL);
        linearLayout.setGravity(CENTER);
        LayoutParams layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT);
        layoutParams.weight = 1;
        if (mDrawable != null) {
            linearLayout.setBackground(mDrawable);
        }
        linearLayout.setFocusable(true);
        linearLayout.setFocusableInTouchMode(true);
        linearLayout.setLayoutParams(layoutParams);
        return linearLayout;
    }

    private int dpFromPx(final float px) {
        return (int) (px / getResources().getDisplayMetrics().density);
    }

    private int pxFromDp(final float dp) {
        return (int) (dp * getResources().getDisplayMetrics().density);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    private int sp2px(float spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 隐藏软键盘
     *
     * @param editText
     */
    void closeInputMethod(View editText) {
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            if (editText != null) {
                inputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
            } else if (((Activity) getContext()).getCurrentFocus() != null
                    && ((Activity) getContext()).getCurrentFocus().getWindowToken() != null) {
                inputMethodManager.hideSoftInputFromWindow(((Activity) getContext()).getCurrentFocus().getWindowToken(), 0);
            }
        }
    }

    /**
     * 显示 软键盘
     *
     * @param editText
     */
    void showInputMethod(View editText) {
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(editText, 0);
        }
    }

    public interface OnItemClickListener {
        void onItemClickListener(boolean isNum, String selectValue);
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
