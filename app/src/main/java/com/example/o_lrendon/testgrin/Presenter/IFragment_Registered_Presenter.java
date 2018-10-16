package com.example.o_lrendon.testgrin.Presenter;

import com.example.o_lrendon.testgrin.Model.Device;
import com.example.o_lrendon.testgrin.Model.ErrorType;

import java.util.List;

public interface IFragment_Registered_Presenter
{
    void GetListDevice();

    void ShowListDevices(final List<Device> deviceList);

    void Error(ErrorType errorType, String message);
}
