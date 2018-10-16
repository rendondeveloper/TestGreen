package com.example.o_lrendon.testgrin.Presenter;

import android.app.Activity;

import com.example.o_lrendon.testgrin.Interactor.Fragment_Search_Interactor;
import com.example.o_lrendon.testgrin.Model.Device;
import com.example.o_lrendon.testgrin.Model.ErrorType;
import com.example.o_lrendon.testgrin.View.IFragment_Search_View;

public class Fragment_Search_Presenter implements IFragment_Search_Presenter{

    final IFragment_Search_View view;
    final Fragment_Search_Interactor interactor;

    public Fragment_Search_Presenter(final IFragment_Search_View view, final Activity activity)
    {
        this.view = view;
        this.interactor = new Fragment_Search_Interactor(this, activity);
    }

    @Override
    public void GetListDevice() {
        this.interactor.GetListDevice();
    }

    @Override
    public void ValidateBluetooth() {
        this.interactor.ValidateBluetooth();
    }

    @Override
    public void ShowDetectedDevice(final Device device){
        this.view.ShowDetectedDevice(device);
    }

    @Override
    public void SuccessValidBluetooth() {
        this.view.SuccessValidBluetooth();
    }

    @Override
    public void Error(final ErrorType errorType, final String message) {
        this.view.Error(errorType, message);
    }

    @Override
    public void Success(final String message) {
        this.view.Success(message);
    }

    @Override
    public void ShowModal(final String message) {
        this.view.ShowModal(message);
    }

    @Override
    public void ClosedModal() {
        this.view.ClosedModal();
    }

    @Override
    public void DeviceUpload(final String nameDevice, final String strength) {
        this.interactor.DeviceUpload(nameDevice, strength);
    }
}
