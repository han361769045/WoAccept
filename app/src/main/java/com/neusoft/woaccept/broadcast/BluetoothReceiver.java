package com.neusoft.woaccept.broadcast;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.text.TextUtils;

import com.neusoft.woaccept.manager.ReadWriteCardManager;
import com.neusoft.woaccept.model.DialogMenuItem;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EReceiver;
import org.androidannotations.annotations.ReceiverAction;
import org.androidannotations.api.support.content.AbstractBroadcastReceiver;

/**
 * Created by LeoLu on 2016/10/1.
 */
@EReceiver
public class BluetoothReceiver extends AbstractBroadcastReceiver {

    private String mDeviceAddress = "";

    @Bean
    ReadWriteCardManager mReadWriteCardManager;

    @ReceiverAction(actions = BluetoothDevice.ACTION_FOUND)
    void actionFound(@ReceiverAction.Extra BluetoothDevice device) {

        if (device.getBondState() != BluetoothDevice.BOND_BONDED) {

            if (!mDeviceAddress.equals(device.getAddress())) {
                mDeviceAddress = device.getAddress();

                String deviceName = device.getName();
                String deviceAddress = device.getAddress();

                if (TextUtils.isEmpty(deviceName))
                    return;
                String new_device = deviceName + "\n" + deviceAddress;
                for (DialogMenuItem dialogMenuItem : mReadWriteCardManager.getPairedList()) {
                    if (dialogMenuItem.deviceName.equals(new_device))
                        return;
                }

                DialogMenuItem item = new DialogMenuItem(new_device, -1);
                item.deviceName = new_device;
                item.deviceAddress = deviceAddress;
//                mNewFindList.add(item);
//                if (deviceDialog != null) {
//
//                    mAllDeviceList.add(item);
//                    deviceDialog.updateListData(mAllDeviceList);
//                }
            }
        }
    }

    @ReceiverAction(actions = BluetoothAdapter.ACTION_DISCOVERY_FINISHED)
    void actionDiscoverFinished() {
        // 处理搜索设备完毕
    }

    @ReceiverAction(actions = BluetoothDevice.ACTION_BOND_STATE_CHANGED)
    void actionBondStateChanged() {

    }

    @ReceiverAction(actions = BluetoothAdapter.ACTION_STATE_CHANGED)
    void actionStateChanged() {

    }


}
