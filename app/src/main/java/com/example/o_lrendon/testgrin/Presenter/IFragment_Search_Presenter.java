package com.example.o_lrendon.testgrin.Presenter;

import com.example.o_lrendon.testgrin.Model.Device;
import com.example.o_lrendon.testgrin.Model.ErrorType;

import java.util.List;

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
