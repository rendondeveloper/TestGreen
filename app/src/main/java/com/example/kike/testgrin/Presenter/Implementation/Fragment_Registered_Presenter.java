package com.example.kike.testgrin.Presenter.Implementation;

import android.app.Activity;

import com.example.kike.testgrin.Interactor.Implementation.Fragment_Registered_Interactor;
import com.example.kike.testgrin.Model.Device;
import com.example.kike.testgrin.Model.ErrorType;
import com.example.kike.testgrin.Presenter.Interface.IFragment_Registered_Presenter;
import com.example.kike.testgrin.View.Interface.IFragment_Registered_View;

import java.util.List;

public class Fragment_Registered_Presenter implements IFragment_Registered_Presenter {

    final private IFragment_Registered_View view;
    final private Fragment_Registered_Interactor interactor;

    public Fragment_Registered_Presenter(final IFragment_Registered_View view, final Activity activity) {
        this.view = view;
        this.interactor = new Fragment_Registered_Interactor(this,activity);
    }

    @Override
    public void GetListDevice() {
        this.interactor.GetListDevice();
    }

    @Override
    public void ShowListDevices(final List<Device> deviceList) {
        this.view.ShowListDevices(deviceList);
    }

    @Override
    public void Error(final ErrorType errorType, final String message) {
        this.view.Error(errorType, message);
    }

}
