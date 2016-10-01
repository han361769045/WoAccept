package com.neusoft.woaccept.customview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.listener.OnOperItemClickL;
import com.neusoft.woaccept.model.DialogMenuItem;
import com.neusoft.woaccept.tools.CornerUtils;

import java.util.ArrayList;

/**
 * Created by lyy on 2016/9/15.
 */

public class NormalListDialog extends BaseDialog {

    //显示正在刷新列表
    private ProgressBar mProgressBar = null;
    /**
     * ListView
     */
    private ListView lv;
    /**
     * title
     */
    private TextView tv_title;
    private FrameLayout mFrameLayout;
    /**
     * corner radius,dp(圆角程度,单位dp)
     */
    private float cornerRadius_DP = 5;
    /**
     * title background color(标题背景颜色)
     */
    private int titleBgColor = Color.parseColor("#303030");
    /**
     * title text(标题)
     */
    private String title = "提示";
    /**
     * title textcolor(标题颜色)
     */
    private int titleTextColor = Color.parseColor("#ffffff");
    /**
     * title textsize(标题字体大小,单位sp)
     */
    private float titleTextSize_SP = 16.5f;
    /**
     * ListView background color(ListView背景色)
     */
    private int lvBgColor = Color.parseColor("#ffffff");
    /**
     * divider color(ListView divider颜色)
     */
    private int dividerColor = Color.LTGRAY;
    /**
     * divider height(ListView divider高度)
     */
    private float dividerHeight_DP = 0.8f;
    /**
     * item press color(ListView item按住颜色)
     */
    private int itemPressColor = Color.parseColor("#ffcccccc");
    /**
     * item textcolor(ListView item文字颜色)
     */
    private int itemTextColor = Color.parseColor("#303030");
    /**
     * item textsize(ListView item文字大小)
     */
    private float itemTextSize_SP = 15f;
    /**
     * item extra padding(ListView item额外padding)
     */
    private int itemExtraLeft;
    private int itemExtraTop;
    private int itemExtraRight;
    private int itemExtraBottom;
    /**
     * enable title show(是否显示标题)
     */
    private boolean isTitleShow = true;
    /**
     * adapter(自定义适配器)
     */
    private BaseAdapter adapter;
    /**
     * operation items(操作items)
     */
    private ArrayList<DialogMenuItem> contents = new ArrayList<>();
    private OnOperItemClickL onOperItemClickL;
    private LayoutAnimationController lac;

    public void setOnOperItemClickL(OnOperItemClickL onOperItemClickL) {
        this.onOperItemClickL = onOperItemClickL;
    }

    public NormalListDialog(Context context, ArrayList<DialogMenuItem> baseItems) {
        super(context);
        this.contents.addAll(baseItems);
        init();
    }

    public NormalListDialog(Context context, String[] items) {
        super(context);
        this.contents = new ArrayList<DialogMenuItem>();
        for (String item : items) {
            DialogMenuItem customBaseItem = new DialogMenuItem(item, 0);
            contents.add(customBaseItem);
        }
        init();
    }

    public NormalListDialog(Context context, BaseAdapter adapter) {
        super(context);
        this.adapter = adapter;
        init();
    }

    private void init() {
        widthScale(0.8f);

        /** LayoutAnimation */
        TranslateAnimation animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 2f, Animation.RELATIVE_TO_SELF,
                0f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.setDuration(550);

        lac = new LayoutAnimationController(animation, 0.12f);
        lac.setInterpolator(new DecelerateInterpolator());
    }

    @Override
    public View onCreateView() {
        LinearLayout ll_container = new LinearLayout(context);
        ll_container.setOrientation(LinearLayout.VERTICAL);
        /** FrameLayout */
//        mFrameLayout = new FrameLayout(context);
//        mFrameLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
//        mFrameLayout.setBackground(getContext().getResources().getDrawable(R.color.black));
//        mFrameLayout.setAlpha(0.2f);
//        ll_container.addView(mFrameLayout);


        /** title */
//        tv_title = new TextView(context);
//        tv_title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
//        tv_title.setSingleLine(true);
//        tv_title.setPadding(dp2px(18), dp2px(10), 0, dp2px(10));
//        tv_title.setGravity(Gravity.CENTER);
//
//        ll_container.addView(tv_title);

        /** listview */
//        lv = new ListView(context);
//        lv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
//        lv.setCacheColorHint(Color.TRANSPARENT);
//        lv.setFadingEdgeLength(0);
////        lv.setVerticalScrollBarEnabled(false);
//        lv.setSelector(new ColorDrawable(Color.TRANSPARENT));
//        ll_container.addView(lv);

        // 取消和刷新的按钮布局
        View view = LayoutInflater.from(context).inflate(R.layout.show_btlist_dialog, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        lv = (ListView) view.findViewById(R.id.lv_equipments);
        mProgressBar = (ProgressBar)view.findViewById(R.id.blue_discover_pro);
        Button cancleButton = (Button) view.findViewById(R.id.cancleconnect_button);

        cancleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
//                Toast.makeText(context, "取消连接", Toast.LENGTH_SHORT).show();
            }
        });

        ll_container.addView(view);

        return ll_container;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void setUiBeforShow() {
        /** title */
        float radius = dp2px(cornerRadius_DP);
        tv_title.setText(title);
        tv_title.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleTextSize_SP);
//        tv_title.setTextColor(context.getResources().getColor(R.color.black));
        tv_title.setVisibility(isTitleShow ? View.VISIBLE : View.GONE);

        /** listview */
        lv.setDivider(new ColorDrawable(dividerColor));
        lv.setDividerHeight(dp2px(dividerHeight_DP));

        if (isTitleShow) {
            lv.setBackgroundDrawable(CornerUtils.cornerDrawable(lvBgColor, new float[]{0, 0, 0, 0, radius, radius, radius,
                    radius}));
        } else {
            lv.setBackgroundDrawable(CornerUtils.cornerDrawable(lvBgColor, radius));
        }

        if (adapter == null) {
            adapter = new ListDialogAdapter();
        }

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onOperItemClickL != null) {
                    onOperItemClickL.onOperItemClick(parent, view, position, id);
                }
            }
        });

        lv.setLayoutAnimation(lac);
    }

    /***
     * set title background color(设置标题栏背景色)
     *
     * @param titleBgColor
     * @return NormalListDialog
     */
    public NormalListDialog titleBgColor(int titleBgColor) {
        this.titleBgColor = titleBgColor;
        return this;
    }

    /**
     * set title text(设置标题内容)
     *
     * @param title
     * @return NormalListDialog
     */
    public NormalListDialog title(String title) {
        this.title = title;
        return this;
    }

    /**
     * set title textsize(设置标题字体大小)
     *
     * @param titleTextSize_SP
     * @return NormalListDialog
     */
    public NormalListDialog titleTextSize_SP(float titleTextSize_SP) {
        this.titleTextSize_SP = titleTextSize_SP;
        return this;
    }

    /**
     * set title textcolor(设置标题字体颜色)
     *
     * @param titleTextColor
     * @return NormalListDialog
     */
    public NormalListDialog titleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return this;
    }

    /**
     * enable title show(设置标题是否显示)
     *
     * @param isTitleShow
     * @return NormalListDialog
     */
    public NormalListDialog isTitleShow(boolean isTitleShow) {
        this.isTitleShow = isTitleShow;
        return this;
    }

    /**
     * set ListView background color(设置ListView背景)
     *
     * @param lvBgColor
     * @return NormalListDialog
     */
    public NormalListDialog lvBgColor(int lvBgColor) {
        this.lvBgColor = lvBgColor;
        return this;
    }

    /**
     * set corner radius(设置圆角程度,单位dp)
     *
     * @param cornerRadius_DP
     * @return NormalListDialog
     */
    public NormalListDialog cornerRadius(float cornerRadius_DP) {
        this.cornerRadius_DP = cornerRadius_DP;
        return this;
    }

    /**
     * set divider color(ListView divider颜色)
     *
     * @param dividerColor
     * @return NormalListDialog
     */
    public NormalListDialog dividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        return this;
    }

    /**
     * set divider height(ListView divider高度)
     *
     * @param dividerHeight_DP
     * @return NormalListDialog
     */
    public NormalListDialog dividerHeight(float dividerHeight_DP) {
        this.dividerHeight_DP = dividerHeight_DP;
        return this;
    }

    /**
     * set item press color(item按住颜色)
     *
     * @param itemPressColor
     * @return NormalListDialog
     */
    public NormalListDialog itemPressColor(int itemPressColor) {
        this.itemPressColor = itemPressColor;
        return this;
    }

    /**
     * set item textcolor(item字体颜色)
     *
     * @param itemTextColor
     * @return NormalListDialog
     */
    public NormalListDialog itemTextColor(int itemTextColor) {
        this.itemTextColor = itemTextColor;
        return this;
    }

    /**
     * set item textsize(item字体大小)
     *
     * @param itemTextSize_SP
     * @return NormalListDialog
     */
    public NormalListDialog itemTextSize(float itemTextSize_SP) {
        this.itemTextSize_SP = itemTextSize_SP;
        return this;
    }

    /**
     * set item height(item高度)
     *
     * @param itemLeft
     * @param itemTop
     * @param itemRight
     * @param itemBottom
     * @return NormalListDialog
     */
    public NormalListDialog setItemExtraPadding(int itemLeft, int itemTop, int itemRight, int itemBottom) {
        this.itemExtraLeft = dp2px(itemLeft);
        this.itemExtraTop = dp2px(itemTop);
        this.itemExtraRight = dp2px(itemRight);
        this.itemExtraBottom = dp2px(itemBottom);

        return this;
    }

    /**
     * set layoutAnimation(设置layout动画 ,传入null将不显示layout动画)
     *
     * @param lac
     * @return NormalListDialog
     */
    public NormalListDialog layoutAnimation(LayoutAnimationController lac) {
        this.lac = lac;
        return this;
    }

    class ListDialogAdapter extends BaseAdapter {

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if(TextUtils.isEmpty(contents.get(position).operName)){
                return 0;
            }else{
                return 1;
            }
        }

        @Override
        public int getCount() {
            return contents.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressWarnings("deprecation")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout ll_item = new LinearLayout(context);
            ll_item.setOrientation(LinearLayout.HORIZONTAL);
            ll_item.setGravity(Gravity.CENTER);
            ll_item.setBackgroundColor(Color.WHITE);
            final DialogMenuItem item = contents.get(position);
            if(1 == getItemViewType(position)) {

                ImageView iv_item = new ImageView(context);
//            iv_item.setPadding(0, 0, dp2px(15), 0);
                ll_item.addView(iv_item);

                TextView tv_item = new TextView(context);
                tv_item.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tv_item.setGravity(Gravity.CENTER);
                tv_item.setSingleLine(false);
                tv_item.setTextColor(itemTextColor);
                tv_item.setTextSize(TypedValue.COMPLEX_UNIT_SP, itemTextSize_SP);

                ll_item.addView(tv_item);
                float radius = dp2px(cornerRadius_DP);
                if (isTitleShow) {
                    ll_item.setBackgroundDrawable((CornerUtils.listItemSelector(radius, Color.TRANSPARENT, itemPressColor,
                            position == contents.size() - 1)));
                } else {
                    ll_item.setBackgroundDrawable(CornerUtils.listItemSelector(radius, Color.TRANSPARENT, itemPressColor,
                            contents.size(), position));
                }

                int left = item.resId == 0 ? dp2px(18) : dp2px(16);
                int top = dp2px(10);
                int right = 0;
                int bottom = dp2px(10);
                ll_item.setPadding(left + itemExtraLeft, top + itemExtraTop, right + itemExtraRight, bottom + itemExtraBottom);

//            iv_item.setImageResource(item.resId);
                tv_item.setText(item.operName);
                iv_item.setVisibility(item.resId == 0 ? View.GONE : View.VISIBLE);

            } else {
                TextView tv_item = new TextView(context);
                tv_item.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tv_item.setBackgroundColor(0x50000000);
                tv_item.setSingleLine(true);
                tv_item.setTextColor(context.getResources().getColor(R.color.white));
                tv_item.setTextSize(TypedValue.COMPLEX_UNIT_SP, itemTextSize_SP);
                if(item.resId == 98)
                    tv_item.setText(R.string.str_paired_devices);
                else
                    tv_item.setText(R.string.str_other_devices);
                ll_item.addView(tv_item);
            }
            return ll_item;
        }
    }

    //更新数据
    public void updateListData(ArrayList<DialogMenuItem> mPairedList) {
        if (adapter != null) {
            contents = mPairedList;
            adapter.notifyDataSetChanged();
        }
    }

    public void startProgress() {
        if(mProgressBar != null && !mProgressBar.isActivated())
            mProgressBar.setVisibility(View.VISIBLE);
    }

    public void endProgress() {
        if(mProgressBar != null) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}
