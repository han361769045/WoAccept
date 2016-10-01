package com.neusoft.woaccept.manager;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.neusoft.woaccept.customview.NormalListDialog;
import com.neusoft.woaccept.listener.OnOperItemClickL;
import com.neusoft.woaccept.model.DialogMenuItem;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by LeoLu on 2016/10/1.
 */
@EBean(scope = EBean.Scope.Singleton)
public class ReadWriteCardManager {

    ArrayList<DialogMenuItem> mAllDeviceList;

    ArrayList<DialogMenuItem> mPairedList;

    ArrayList<DialogMenuItem> mNewFindList;

    NormalListDialog deviceDialog;

    ProgressDialog progressDialog;

    BluetoothAdapter btAdapter;

    @RootContext
    Context mContext;

    @StringRes
    String conning;

    //蓝牙设备列表dialog
    void prepareBlueToothDevice() {
        mAllDeviceList = new ArrayList<>();
        mPairedList = new ArrayList<>();

        DialogMenuItem i = new DialogMenuItem("", 98);
        mAllDeviceList.add(i);
        mAllDeviceList.addAll(mPairedList);
        DialogMenuItem ii = new DialogMenuItem("", 99);
        mAllDeviceList.add(ii);
        deviceDialog = new NormalListDialog(mContext, mAllDeviceList);
        deviceDialog.lvBgColor(Color.parseColor("#FFFFFF")); // 设置item背景颜色
        deviceDialog.title("设备列表")
                .titleBgColor(Color.parseColor("#C4000E"))
                .isTitleShow(true)//
//                .itemPressColor(Color.parseColor("#85D3EF"))// item按下时的颜色
                .itemTextColor(Color.parseColor("#303030"))//
                .itemTextSize(13)//
//                .cornerRadius(5)// 圆角半径
                .widthScale(0.75f)//
                .show();
        deviceDialog.setCanceledOnTouchOutside(false);
        deviceDialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override
            public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogMenuItem item = mAllDeviceList.get(position);
                if (item.isParied) {
                    createPro();
//                    connect(item);
                    deviceDialog.dismiss();
                } else {
                    if (!TextUtils.isEmpty(item.deviceName)) {
//                        pair(item, "123456");
                        deviceDialog.dismiss();
                    }
                }
            }
        });
    }

    private void createPro() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mContext);
            progressDialog.setCancelable(false);
            progressDialog.setMessage(conning);
            progressDialog.show();
        } else
            progressDialog.show();
    }

    private void initData() {
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (btAdapter != null) {
            if (!btAdapter.isEnabled()) {
                boolean isOk = turnOnBluetooth();
                if (!isOk) {
//                    ToastUtil.showToast(mContext, "请打开蓝牙!");
                }
            }
        }
    }

    /**
     * 列出已配对的蓝牙
     */
    public void listPairedBT() {

        IntentFilter intent = new IntentFilter();
        intent.addAction(BluetoothDevice.ACTION_FOUND);
        intent.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        intent.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);
        intent.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        intent.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//        mContext.registerReceiver(mReceiver, intent);

//        if (mPairedList != null) {
//            mPairedList.clear();
//        }
//        if (mNewFindList != null) {
//            mNewFindList.clear();
//        }

        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                String nameAddress = device.getName() + "\n" + device.getAddress();
                DialogMenuItem item = new DialogMenuItem(nameAddress, -1);
                item.isParied = true;
                item.deviceName = nameAddress;
                item.deviceAddress = device.getAddress();
                mPairedList.add(item);
//                mPairedCheck.add(1);
            }
        }
    }

    /**
     * 强制开启当前 Android 设备的 Bluetooth
     *
     * @return true：强制打开 Bluetooth　成功　false：强制打开 Bluetooth 失败
     */

    private boolean turnOnBluetooth() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            return bluetoothAdapter.enable();
        }
        return false;
    }

    public ArrayList<DialogMenuItem> getAllDeviceList() {
        return mAllDeviceList;
    }

    public void setAllDeviceList(ArrayList<DialogMenuItem> allDeviceList) {
        mAllDeviceList = allDeviceList;
    }

    public ArrayList<DialogMenuItem> getPairedList() {
        return mPairedList;
    }

    public void setPairedList(ArrayList<DialogMenuItem> pairedList) {
        mPairedList = pairedList;
    }
}
