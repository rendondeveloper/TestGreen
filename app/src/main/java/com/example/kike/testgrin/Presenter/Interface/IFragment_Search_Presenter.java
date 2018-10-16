package com.example.kike.testgrin.Presenter.Interface;

import com.example.kike.testgrin.Model.Device;
import com.example.kike.testgrin.Model.ErrorType;

public interface IFragment_Search_Presenter
{
    void GetListDevice();

    void ValidateBluetooth();

    void ShowDetectedDevice(Device device);

    void SuccessValidBluetooth();

    void Error(ErrorType errorType, String message);

    void Success(String message);

    void ShowModal(String message);

    void ClosedModal();

    void DeviceUpload(String nameDevice, String strength);
}
