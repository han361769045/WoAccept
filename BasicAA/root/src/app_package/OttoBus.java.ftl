package ${packageName}.listener;

import android.util.Log;

import com.squareup.otto.Bus;

import org.androidannotations.annotations.EBean;

@EBean(scope = EBean.Scope.Singleton)
public class OttoBus extends Bus {

    private final String TAG = this.getClass().getPackage().getName();

    public void register(Object object) {
        try {
            super.register(object);
        } catch (IllegalArgumentException exception) {
            Log.e(TAG, exception.getMessage());
        }
    }

    public void unregister(Object object) {
        try {
            super.unregister(object);
        } catch (IllegalArgumentException exception) {
            Log.e(TAG, exception.getMessage());
        }
    }
}
