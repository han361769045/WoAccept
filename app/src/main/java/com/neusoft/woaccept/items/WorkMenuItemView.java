package com.neusoft.woaccept.items;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.model.WorkMenuBean;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

/**
 * Created by LeoLu on 2016/9/23.
 */
@EViewGroup(R.layout.fragment_home_work_menu_item)
public class WorkMenuItemView extends ItemView<WorkMenuBean> {

    @ViewById
    ImageView home_item_icon;

    @ViewById
    TextView home_item_title;


    public WorkMenuItemView(Context context) {
        super(context);
    }

    @Override
    protected void init(Object... objects) {
        home_item_title.setText(_data.getMenuName());
//        if (!StringUtils.isEmpty(_data.getF_menu_desc())) {
//            Glide.with(context).load(Constants.IMAGE_URL + _data.getF_menu_desc())
//                    .centerCrop()
//                    .crossFade()
//                    .into(home_item_icon);
//        }
        switch (_data.getMenuId()) {
            //新装入
            case "pre_m":
                home_item_icon.setImageResource(R.drawable.menu_guhua);
                break;
            //现金缴费
            case "pre_g":
                home_item_icon.setImageResource(R.drawable.menu_jiaofei);
                break;
            //订购流量包
            case "pre_d":
                home_item_icon.setImageResource(R.drawable.menu_liuliang);
                break;
            //缴费返销
            case "pre_ab":
                home_item_icon.setImageResource(R.drawable.menu_xinzhuang);
                break;
            //宽带入网
            case "pre_i":
                home_item_icon.setImageResource(R.drawable.menu_kuandai);
                break;
            //预登录返单
            case "pre_lmx2":
                home_item_icon.setImageResource(R.drawable.menu_yudenglu);
                break;
        }

    }

    @Override
    public void onItemSelected() {

    }

    @Override
    public void onItemClear() {

    }
}
