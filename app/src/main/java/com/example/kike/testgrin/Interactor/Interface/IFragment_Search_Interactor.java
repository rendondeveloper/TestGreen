package com.example.kike.testgrin.Interactor.Interface;

import com.example.kike.testgrin.Model.ErrorType;

public interface IFragment_Search_Interactor
{
    void GetListDevice();

    void ValidateBluetooth();

    void DeviceUpload(String nameDevice, String strength);

    void Error(ErrorType errorType, String message);

    void Success(String message);
}
