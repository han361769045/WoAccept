package ${packageName}.tools;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by LeoLu on 2016/10/27.
 */

public class AndroidTool {

    private static ProgressDialog infoDialog;

    /**
     * 显示等待对话框
     *
     * @param context
     */
    public static void showLoadDialog(Context context) {
        if (infoDialog == null) {
            infoDialog = new ProgressDialog(context);
            infoDialog.setCanceledOnTouchOutside(false);
            infoDialog.show();
        } else if (!infoDialog.isShowing() && infoDialog.getContext() == context) {
            infoDialog.setCanceledOnTouchOutside(false);
            infoDialog.show();
        } else if (!infoDialog.isShowing() && infoDialog.getContext() != context) {
            infoDialog = new ProgressDialog(context);
            infoDialog.setCanceledOnTouchOutside(false);
            infoDialog.show();
        }
    }

    /**
     * 隐藏等待对话框
     */
    public static void dismissLoadDialog() {
        if (infoDialog != null && infoDialog.isShowing()) {
            infoDialog.dismiss();
        }
    }

    /**
     * 显示 Toast
     *
     * @param context
     * @param msg     消息
     */
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示 Toast
     *
     * @param fragment
     * @param msg      消息
     */
    public static void showToast(android.support.v4.app.Fragment fragment, String msg) {
        Toast.makeText(fragment.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示 Toast
     *
     * @param msg 消息
     */
    public static void showToast(Fragment fragment, String msg) {
        Toast.makeText(fragment.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

}
