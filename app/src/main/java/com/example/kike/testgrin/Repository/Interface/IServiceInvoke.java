package com.example.kike.testgrin.Repository.Interface;

import com.example.kike.testgrin.Model.ResponseCreateDevice;
import com.example.kike.testgrin.Model.ResponseDevice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IServiceInvoke
{
    @GET("all")
    Call<ResponseDevice> GetListDevice(@Query("order") String order);

    @POST("create")
    Call<ResponseCreateDevice> ExecuteSaveDevice(@Body String body);

}
