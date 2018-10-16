package com.example.o_lrendon.testgrin.Presenter;

import android.app.Activity;

import com.example.o_lrendon.testgrin.Interactor.Fragment_Registered_Interactor;
import com.example.o_lrendon.testgrin.Model.Device;
import com.example.o_lrendon.testgrin.Model.ErrorType;
import com.example.o_lrendon.testgrin.View.IFragment_Registered_View;

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
