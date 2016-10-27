package ${packageName}.fragments;


import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import org.androidannotations.annotations.App;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SystemService;
import org.androidannotations.annotations.res.StringRes;


import ${packageName}.MyApplication;




@EFragment
public abstract class BaseFragment extends Fragment {


    @SystemService
    LayoutInflater layoutInflater;

    @StringRes
    String no_net;

    @App
    MyApplication app;
   
    @Override
    public void onPause() {
        super.onPause();
        if (isVisible()) {
            onHiddenChanged(isVisible());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isVisible()) {
            onHiddenChanged(isHidden());
        }
    }
}
