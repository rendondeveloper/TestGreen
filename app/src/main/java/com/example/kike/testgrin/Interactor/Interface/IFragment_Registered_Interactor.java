package com.example.kike.testgrin.Interactor.Interface;

import com.example.kike.testgrin.Model.Device;
import com.example.kike.testgrin.Model.ErrorType;

import java.util.List;

public interface IFragment_Registered_Interactor
{
    void GetListDevice();

    void ShowListDevices(List<Device> deviceList);

    void Error(ErrorType errorType, String message);
}
