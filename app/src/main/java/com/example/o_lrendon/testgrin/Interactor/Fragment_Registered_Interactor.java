package com.example.o_lrendon.testgrin.Interactor;

import android.app.Activity;

import com.example.o_lrendon.testgrin.Model.Device;
import com.example.o_lrendon.testgrin.Model.ErrorType;
import com.example.o_lrendon.testgrin.Presenter.IFragment_Registered_Presenter;
import com.example.o_lrendon.testgrin.R;
import com.example.o_lrendon.testgrin.Repository.ServiceInvoke;

import java.util.List;

public class Fragment_Registered_Interactor implements IFragment_Registered_Interactor
{
    final private IFragment_Registered_Presenter presenter;
    final private ServiceInvoke serviceInvoke;
    final private Activity activity;

    public Fragment_Registered_Interactor(IFragment_Registered_Presenter presenter, final Activity activity) {
        this.presenter = presenter;
        this.activity = activity;
        this.serviceInvoke = new ServiceInvoke(this, activity);
    }

    @Override
    public void GetListDevice() {
        this.serviceInvoke.GetListDevice("1");
    }

    @Override
    public void ShowListDevices(final List<Device> deviceList) {
        this.presenter.ShowListDevices(deviceList);
    }

    @Override
    public void Error(final ErrorType errorType, final String message) {
        this.presenter.Error(errorType, message);
    }
}
