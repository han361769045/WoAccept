package com.neusoft.woaccept.tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.neusoft.woaccept.R;
import com.neusoft.woaccept.customview.CustomProgressDialog;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AndroidTool {
    private static ProgressDialog infoDialog;
    private static CustomProgressDialog cpdialog;
    private static String MPHONE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
    private static String TPHONE = "^(0\\d{2}-\\d{8}(-\\d{1,4})?)|(0\\d{3}-\\d{7,8}(-\\d{1,4})?)$";
    public static DecimalFormat df = new DecimalFormat("######0.00");
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");


    private static DecimalFormat dft = new DecimalFormat("0.00");

    public static String toDouble(double par) {
        return dft.format(par);
    }

    //    /**
//     * 显示等待对话框
//     *
//     * @param context
//     */
//    public static void showLoadDialog(Context context) {
//        if (infoDialog == null) {
//            infoDialog = new LoadingDialog(context, "正在加载..", R.anim.loading);
//            infoDialog.setCancelable(false);
//            infoDialog.show();
//        } else if (!infoDialog.isShowing()&& infoDialog.getContext() == context) {
//            infoDialog.setCancelable(false);
//            infoDialog.show();
//        } else if (!infoDialog.isShowing()&&infoDialog.getContext() != context) {
//            infoDialog = new LoadingDialog(context, "正在加载..",R.anim.loading);
//            infoDialog.setCancelable(false);
//            infoDialog.show();
//        }
//    }
    public static String getRandomOrdersId(String str) {
        String orderIds = "";
        StringBuilder builder = new StringBuilder();
        Random random = new Random();// 指定种子数字
        for (int i = 0; i < 11; i++) {
            builder.append(String.valueOf(random.nextInt(9)));
        }
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMM");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        orderIds = retStrFormatNowDate + builder.toString();
        orderIds = str + orderIds;
        return orderIds;
    }

    public static String getOccupyTime() {
//      Date nowTime = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 30);
        Date nowTime = calendar.getTime();
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdFormatter.format(nowTime);
    }

    public static String convertDouble(String num) {
        String result = "0.00";
        if (num != null && !"".equals(num)) {
            DecimalFormat df = new DecimalFormat("0.00");
            Double db = Double.valueOf(num) / 100;
            result = String.valueOf(df.format(db));
        }
        return result;
    }


    public static long getCodeTime(long timer) {
        return Math.abs(System.currentTimeMillis() - timer) >= 120000L ? 120000L : Math.abs(System.currentTimeMillis() - timer);
    }


    public static String getCurrentFirstDay(int month) {
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, 1 - month);
        cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
        return format.format(cal_1.getTime());
    }

    public static String getCurrentDay() {
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        return format.format(cal_1.getTime());
    }


    public static String getCurrentLastDay() {
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH, 0);//设置为1号,当前日期既为本月第一天
        return format.format(cale.getTime());
    }

    public static String getYYYYMMDDHHMMSS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }

    public static String changeLongToDate(long l) {

        return sdf.format(new Date(l));
    }


    /**
     * 显示等待对话框
     *
     * @param context
     */
    public static void showLoadDialog(final Context context) {
        if (cpdialog == null) {
            cpdialog = CustomProgressDialog.createDialog(context);
            cpdialog.setCanceledOnTouchOutside(false);
//            cpdialog.setCancelable(false);
            cpdialog.show();
        } else if (!cpdialog.isShowing() && cpdialog.getContext() == context) {
            cpdialog.setCanceledOnTouchOutside(false);
            cpdialog.show();
        } else if (!cpdialog.isShowing() && cpdialog.getContext() != context) {
            cpdialog = CustomProgressDialog.createDialog(context);
//            cpdialog.setCanceledOnTouchOutside(false);
//            cpdialog.setCancelable(false);
            cpdialog.show();
        }
    }

    public static void showLoadDialog(final Fragment context) {
        if (cpdialog == null) {
            cpdialog = CustomProgressDialog.createDialog(context.getActivity());
            cpdialog.setCanceledOnTouchOutside(false);
//            cpdialog.setCancelable(false);
            cpdialog.show();
        } else if (!cpdialog.isShowing() && cpdialog.getContext() == context.getActivity()) {
            cpdialog.setCanceledOnTouchOutside(false);
            cpdialog.show();
        } else if (!cpdialog.isShowing() && cpdialog.getContext() != context.getActivity()) {
            cpdialog = CustomProgressDialog.createDialog(context.getActivity());
            cpdialog.setCanceledOnTouchOutside(false);
//            cpdialog.setCancelable(false);
            cpdialog.show();
        }
    }


    public static void showCancelabledialog(Context context) {
        if (cpdialog == null) {
            cpdialog = CustomProgressDialog.createDialog(context);
            cpdialog.setCancelable(false);
            cpdialog.show();
        } else if (!cpdialog.isShowing() && cpdialog.getContext() == context) {
            cpdialog.setCancelable(false);
            cpdialog.show();
        } else if (!cpdialog.isShowing() && cpdialog.getContext() != context) {
            cpdialog = CustomProgressDialog.createDialog(context);
            cpdialog.setCancelable(false);
            cpdialog.show();
        }
    }

    public static void showdialog(Context context) {
        if (cpdialog == null) {
            cpdialog = CustomProgressDialog.createDialog(context);
            cpdialog.setCanceledOnTouchOutside(false);
            cpdialog.show();
        } else if (!cpdialog.isShowing() && cpdialog.getContext() == context) {
            cpdialog.setCanceledOnTouchOutside(false);
            cpdialog.show();
        } else if (!cpdialog.isShowing() && cpdialog.getContext() != context) {
            cpdialog = CustomProgressDialog.createDialog(context);
            cpdialog.setCanceledOnTouchOutside(false);
            cpdialog.show();
        }
    }

    /**
     * 隐藏等待对话框
     */
    public static void dismissdialog(final Context context) {
        if (cpdialog != null && cpdialog.isShowing()) {
            cpdialog.dismiss();
        }
    }


    /**
     * 判断是否为数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 隐藏等待对话框
     */
    public static void dismissLoadDialog() {
        if (cpdialog != null && cpdialog.isShowing()) {
            cpdialog.dismiss();
        }
    }

//    /**
//     * 隐藏等待对话框
//     */
//    public static void dismissLoadDialog() {
//        if (infoDialog != null && infoDialog.isShowing()) {
//            infoDialog.dismiss();
//        }
//    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Activity context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Fragment context, String message) {
        Toast.makeText(context.getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(android.app.Fragment context, String message) {
        Toast.makeText(context.getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Activity context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Fragment context, int resId) {
        Toast.makeText(context.getActivity(), resId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(android.app.Fragment context, int resId) {
        Toast.makeText(context.getActivity(), resId, Toast.LENGTH_SHORT).show();
    }

    public static void showCustomToast(Activity context, String message) {
        Toast toast = new Toast(context);
        LayoutInflater inflate = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflate.inflate(R.layout.custom_toast, null);
        ImageView icon = (ImageView) v.findViewById(R.id.img_icon);
        TextView tv = (TextView) v.findViewById(R.id.txt_message);
        tv.setText(message);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(v);
        toast.show();
    }

    public static void showCustomToast(Activity context, int resId) {
        showCustomToast(context, context.getResources().getString(resId));
    }


    /**
     * @param context
     * @param title    标题
     * @param items    元素
     * @param checkId  当前元素的id
     * @param listener 监听器
     */
    public static void showSinglenChoice(Context context, String title,
                                         String[] items, int checkId,
                                         DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setSingleChoiceItems(items, checkId > 0 ? checkId : 0,
                        listener).create().show();
    }

    public static Dialog showCustomDialogNoTitle(Context context, View view) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        return dialog;
    }


    /**
     * @param title    标题
     * @param view     编辑内容
     * @param listener 监听器
     * @return
     */
    public static Dialog showEditDialog(String title, EditText view,
                                        DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(title);
        builder.setView(view);
        builder.setPositiveButton("确定", listener);
        return builder.create();
    }


    public static Dialog showViewDialog(String title, View view,
                                        DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setTitle(title);
        builder.setView(view);
        builder.setPositiveButton("确定", listener);
        return builder.create();
    }

    /**
     * 判断是否为空
     *
     * @param context
     * @param txts
     * @return
     */
    public static boolean checkNotNull(Context context, String... txts) {
        for (String t : txts) {
            if (t == null || t.length() == 0) {
                if (context != null)
                    showToast(context, "请正确填写！！！");
                return false;
            }
        }
        return true;
    }

    /***
     * 去掉数组中重复的 元素
     *
     * @param resource
     * @return String[]
     */
    public static String[] Array_unique(String[] resource) {
        // array_unique
        List<String> list = new LinkedList<String>();
        for (int i = 0; i < resource.length; i++) {
            if (!list.contains(resource[i])) {
                list.add(resource[i]);
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * 判断EditText是否为空！ 如果为空 返回true 否者返回false
     *
     * @param e
     * @return
     */
    public static boolean checkIsNull(EditText e) {
        if (e == null) {
            return true;
        }
        return "".equals(e.getText().toString().trim());
    }

    /**
     * 判断EditText是否为空！ 如果为空 返回false true
     *
     * @param e
     * @return
     */
    public static boolean checkEditText(EditText... e) {
        if (e == null) {
            return false;
        }
        for (EditText t : e) {
            if ("".equals(t.getText().toString().trim()) || t.getText().toString().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断TextView是否为空！ 如果为空 返回true 否者返回false
     *
     * @param e
     * @return
     */
    public static boolean checkTextViewIsNull(TextView e) {
        if (e == null) {
            return true;
        }
        return "".equals(e.getText().toString().trim());
    }

    /**
     * 判断TextView是否为空！ 如果为空 返回true 否者返回false
     *
     * @param e
     * @return
     */
    public static boolean checkTextViewIsNull(TextView... e) {
        if (e == null) {
            return true;
        }
        for (TextView t : e) {
            if ("".equals(t.getText().toString().trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断手机号码 是否有误！ 如果有误 返回true 否者返回false
     *
     * @param mPhone
     * @return
     */
    public static boolean checkMPhone(EditText mPhone) {
        Pattern p = Pattern.compile(MPHONE);
        Matcher m = p.matcher(mPhone.getText().toString().trim());

        return !m.matches();
    }


    /**
     * 判断手机号码 是否有误！ 如果有误 返回true 否者返回false
     *
     * @param mPhone
     * @return
     */
    public static boolean checkMPhone(String mPhone) {
        Pattern p = Pattern.compile(MPHONE);
        Matcher m = p.matcher(mPhone.trim());
        return !m.matches();
    }

    /**
     * 判断座机号码 是否有误！ 如果有误 返回true 否者返回false
     *
     * @param tPhone
     * @return
     */
    public static boolean checkTPhone(String tPhone) {
        Pattern p = Pattern.compile(TPHONE);
        Matcher m = p.matcher(tPhone.trim());
        return !m.matches();
    }


    /**
     * 判断手机号码 是否有误！ 如果有误 返回true 否者返回false
     *
     * @param mPhone
     * @return
     */
    public static boolean checkMPhone(TextView mPhone) {
        Pattern p = Pattern.compile(MPHONE);
        Matcher m = p.matcher(mPhone.getText().toString().trim());

        return !m.matches();
    }


    /**
     * 判断邮箱 是否有误！ 如果有误 返回true 否者返回false
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(TextView email) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(check);
        Matcher m = p.matcher(email.getText().toString().trim());
        return !m.matches();
    }

    /**
     * 判断邮箱 是否有误！ 如果有误 返回true 否者返回false
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(EditText email) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(check);
        Matcher m = p.matcher(email.getText().toString().trim());
        return !m.matches();
    }

    /**
     * 判断邮箱 是否有误！ 如果有误 返回true 否者返回false
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p = Pattern.compile(check);
        Matcher m = p.matcher(email.trim());
        return !m.matches();
    }


    /**
     * 判断座机号码 是否有误！ 如果有误 返回true 否者返回false
     *
     * @param tPhone
     * @return
     */
    public static boolean checkTPhone(EditText tPhone) {
        Pattern p = Pattern.compile(TPHONE);
        Matcher m = p.matcher(tPhone.getText().toString().trim());
        return !m.matches();
    }

    public static boolean isNetConnected(Context context) {
        boolean isNetConnected;
        // 获得网络连接服务
        ConnectivityManager connManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            // String name = info.getTypeName();
            // L.i("当前网络名称：" + name);
            isNetConnected = true;
        } else {
            isNetConnected = false;
        }
        return isNetConnected;
    }

    /**
     * 将文件转换为Uri
     *
     * @param fileName
     * @return
     */
    public static Uri getUri(String fileName) {
        return Uri.fromFile(GetFile(fileName));
    }

    /**
     * 创建文件对象
     *
     * @param fileName
     * @return
     */
    public static File GetFile(String fileName) {
        File file = new File(fileName);
        return file;
    }

    /**
     * 获取图片跟路径地址
     *
     * @return
     */
    public static String BaseFilePath() {
        StringBuffer sb = new StringBuffer();
        sb.append(Environment.getExternalStorageDirectory().getPath());
        sb.append("/download_cache/");
        File file = new File(sb.toString());
        if (!file.exists()) {
            file.mkdirs();
        }
        return sb.toString();
    }

    public static String test(String str) {


        return "";
    }

    // 校验Tag Alias 只能是数字,英文字母和中文
    public static boolean isValidTagAndAlias(String s) {
        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_-]{0,}$");
        Matcher m = p.matcher(s);
        return m.matches();
    }

}
