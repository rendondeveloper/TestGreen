package com.example.kike.testgrin.View.Interface;

import com.example.kike.testgrin.Model.Device;
import com.example.kike.testgrin.Model.ErrorType;


public interface IFragment_Search_View
{
    void ShowDetectedDevice(Device device);

    void Error(ErrorType errorType, String message);

    void Success(String message);

    void SuccessValidBluetooth();

    void ShowModal(String message);

    void ClosedModal();

    void DeviceUpload(String nameDevice, String strength);
}
