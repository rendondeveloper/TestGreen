package com.example.kike.testgrin.Presenter.Interface;

import com.example.kike.testgrin.Model.Device;
import com.example.kike.testgrin.Model.ErrorType;

import java.util.List;

public interface IFragment_Registered_Presenter
{
    void GetListDevice();

    void ShowListDevices(final List<Device> deviceList);

    void Error(ErrorType errorType, String message);
}
