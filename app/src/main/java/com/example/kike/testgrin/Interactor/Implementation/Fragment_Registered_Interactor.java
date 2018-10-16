package com.example.kike.testgrin.Interactor.Implementation;

import android.app.Activity;

import com.example.kike.testgrin.Interactor.Interface.IFragment_Registered_Interactor;
import com.example.kike.testgrin.Model.Device;
import com.example.kike.testgrin.Model.ErrorType;
import com.example.kike.testgrin.Presenter.Interface.IFragment_Registered_Presenter;
import com.example.kike.testgrin.Repository.Implementation.ServiceInvoke;

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
