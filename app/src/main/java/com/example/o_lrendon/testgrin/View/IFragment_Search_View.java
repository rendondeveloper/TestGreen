package com.example.o_lrendon.testgrin.View;

import com.example.o_lrendon.testgrin.Model.Device;
import com.example.o_lrendon.testgrin.Model.ErrorType;


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
