package com.example.o_lrendon.testgrin.Repository;

import com.example.o_lrendon.testgrin.Model.ResponseCreateDevice;
import com.example.o_lrendon.testgrin.Model.ResponseDevice;
import com.google.gson.JsonObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface IServiceInvoke
{
    @GET("all")
    Call<ResponseDevice> GetListDevice(@Query("order") String order);

    @POST("create")
    Call<ResponseCreateDevice> ExecuteSaveDevice(@Body String body);

}
