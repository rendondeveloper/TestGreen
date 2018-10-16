package com.example.o_lrendon.testgrin.Interactor;

import com.example.o_lrendon.testgrin.Model.Device;
import com.example.o_lrendon.testgrin.Model.ErrorType;

import java.util.List;

public interface IFragment_Registered_Interactor
{
    void GetListDevice();

    void ShowListDevices(List<Device> deviceList);

    void Error(ErrorType errorType, String message);
}
