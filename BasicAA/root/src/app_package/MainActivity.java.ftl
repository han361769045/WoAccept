package ${packageName}.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import ${packageName}.customview.FragmentTabHost;
import ${packageName}.tools.AndroidTool;

import ${packageName}.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringArrayRes;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewById
    FragmentTabHost tabHost;

	@StringArrayRes
    String[] tabTag, tabTitle;
	
	//导航
    Class[] classTab = {};
	
	int[] ids = {R.mipmap.ic_launcher};
	
    long firstTime = 0;

    @AfterViews
    void afterView() {
        initTab();
    }
	
	protected void initTab() {
		tabHost.setup(this, getSupportFragmentManager(), R.id.real_content);
		for (int i = 0; i < tabTag.length; i++) {
			Bundle bundle = new Bundle();
			TabHost.TabSpec tabSpec = tabHost.newTabSpec(tabTag[i]);
			tabSpec.setIndicator(buildIndicator(i));
			tabHost.addTab(tabSpec, classTab[i], bundle);
		}
		tabHost.setCurrentTab(0);
		tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {
			}
		});
	}
	
	protected View buildIndicator(int position) {
        View view = layoutInflater.inflate(R.layout.tab_indicator, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon_tab);
        TextView textView = (TextView) view.findViewById(R.id.text_indicator);
        imageView.setImageResource(ids[position]);
        textView.setText(tabTitle[position]);
        return view;
    }
	
	@Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            AndroidTool.showToast(this, "再按一次退出程序");
            firstTime = secondTime;
        } else {
            finish();
            System.exit(-1);
        }
    }
}
