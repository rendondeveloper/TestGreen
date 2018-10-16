package com.example.o_lrendon.testgrin.Interactor;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.o_lrendon.testgrin.Model.Device;
import com.example.o_lrendon.testgrin.Model.ErrorType;
import com.example.o_lrendon.testgrin.Presenter.IFragment_Registered_Presenter;
import com.example.o_lrendon.testgrin.Presenter.IFragment_Search_Presenter;
import com.example.o_lrendon.testgrin.R;
import com.example.o_lrendon.testgrin.Repository.ServiceInvoke;

public class Fragment_Search_Interactor implements IFragment_Search_Interactor{

    final private IFragment_Search_Presenter presenter;
    final private BluetoothAdapter bluetoothAdapter;
    final private ServiceInvoke serviceInvoke;
    final private Activity activity;

    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction())
            {
                case BluetoothAdapter.ACTION_DISCOVERY_STARTED:
                    presenter.ShowModal(activity.getString(R.string.modal_device_search));
                    break;

                case BluetoothDevice.ACTION_FOUND:
                    final BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                    if(device != null)
                    {
                        final int  strength = intent.getShortExtra(BluetoothDevice.EXTRA_RSSI,Short.MIN_VALUE);
                        presenter.ShowDetectedDevice(new Device(device.getName(), String.valueOf(strength)));
                    }
                    break;

                case BluetoothAdapter.ACTION_DISCOVERY_FINISHED:
                    presenter.ClosedModal();
                    break;
            }

        }
    };


    public Fragment_Search_Interactor(final IFragment_Search_Presenter presenter, final Activity activity) {
        this.presenter = presenter;
        this.activity = activity;
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        this.serviceInvoke = new ServiceInvoke(this, activity);

        final IntentFilter filterActionFound = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        this.activity.registerReceiver(this.broadcastReceiver, filterActionFound);

        final IntentFilter filterDiscoveryFinish = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.activity.registerReceiver(this.broadcastReceiver, filterDiscoveryFinish);

        final IntentFilter filterDiscoveryStarted = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        this.activity.registerReceiver(this.broadcastReceiver, filterDiscoveryStarted);
    }

    @Override
    public void GetListDevice()
    {
        if(bluetoothAdapter.isDiscovering())
        {
            bluetoothAdapter.cancelDiscovery();
        }

        final boolean successScan = bluetoothAdapter.startDiscovery();

        if(!successScan)
        {
            this.presenter.Error(ErrorType.NotSupportBluettoth, this.activity.getString(R.string.message_device_not_search_device));
        }
    }

    @Override
    public void ValidateBluetooth() {

        if(bluetoothAdapter == null)
        {
            this.presenter.Error(ErrorType.NotSupportBluettoth, this.activity.getString(R.string.message_device_not_support_bluetooth));
        }
        else if(!bluetoothAdapter.isEnabled())
        {
            this.presenter.Error(ErrorType.BluettothOff, "");
        }
        else
        {
            this.presenter.SuccessValidBluetooth();
        }
    }

    @Override
    public void DeviceUpload(final String nameDevice, final String strength) {
        this.serviceInvoke.ExecuteSaveDevice(nameDevice, strength);
    }

    @Override
    public void Error(final ErrorType errorType, final String message) {
        this.presenter.Error(errorType, message);
    }

    @Override
    public void Success(final String message) {
        this.presenter.Success(message);
    }
}
