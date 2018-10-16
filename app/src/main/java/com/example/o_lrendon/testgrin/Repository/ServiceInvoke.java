package com.example.o_lrendon.testgrin.Repository;

import android.app.Activity;

import com.example.o_lrendon.testgrin.Interactor.IFragment_Registered_Interactor;
import com.example.o_lrendon.testgrin.Interactor.IFragment_Search_Interactor;
import com.example.o_lrendon.testgrin.Model.Device;
import com.example.o_lrendon.testgrin.Model.ErrorType;
import com.example.o_lrendon.testgrin.Model.ResponseCreateDevice;
import com.example.o_lrendon.testgrin.Model.ResponseDevice;
import com.example.o_lrendon.testgrin.R;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceInvoke
{
    final private IFragment_Registered_Interactor interactor;
    final private IFragment_Search_Interactor interactorSearch;
    final private Activity activity;
    private Gson gson = null;
    private Retrofit retrofit = null;
    private IServiceInvoke serviceInvoke = null;
    private Call<ResponseDevice> invoke = null;
    private Call<ResponseCreateDevice> invokePost = null;


    public ServiceInvoke(final IFragment_Registered_Interactor interactor, final Activity activity) {
        this.interactor = interactor;
        this.activity = activity;
        this.interactorSearch = null;
    }

    public ServiceInvoke(final IFragment_Search_Interactor interactor, final Activity activity) {
        this.interactorSearch = interactor;
        this.activity = activity;
        this.interactor = null;
    }

    private void loadJsonRequest()
    {
        if(this.gson == null) {
            this.gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss'Z'")
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create();
        }

        if(this.retrofit == null) {
            this.retrofit = new Retrofit
                    .Builder()
                    .baseUrl("http://mock.westcentralus.cloudapp.azure.com/grin_test/bluetooth/")
                    .addConverterFactory(GsonConverterFactory.create(this.gson))
                    .build();
        }
    }

    public void ExecuteSaveDevice(final String name,final String strength)
    {
        loadJsonRequest();

        if(this.serviceInvoke == null){
            this.serviceInvoke = retrofit.create(IServiceInvoke.class);
        }

        this.invokePost = this.serviceInvoke.ExecuteSaveDevice("{\"body\": \"sssss\",\"name\": \""+name+"\",\"strength\": \""+strength+"\"}");

        this.invokePost.enqueue(new Callback<ResponseCreateDevice>() {
            @Override
            public void onResponse(Call<ResponseCreateDevice> call, Response<ResponseCreateDevice> response) {
                if (response != null && response.isSuccessful())
                {
                    final ResponseCreateDevice responseCreateDevice = response.body();
                    if(responseCreateDevice.getStatus() != null && responseCreateDevice.getStatus().toLowerCase().equals("ok")) {
                        interactorSearch.Success(activity.getString(R.string.message_device_register_success));
                    }
                    else
                    {
                        interactorSearch.Error(ErrorType.Fail, activity.getString(R.string.message_fail_device_register_success));
                    }
                }
                else
                {
                    interactorSearch.Error(ErrorType.Fail, activity.getString(R.string.message_fail_device_register_success));
                }
            }

            @Override
            public void onFailure(Call<ResponseCreateDevice> call, Throwable t) {
                interactorSearch.Error(ErrorType.Fail, activity.getString(R.string.message_fail_device_register_success));
            }
        });
    }

    public void GetListDevice(final String order)
    {
        loadJsonRequest();

        if(this.serviceInvoke == null){
            this.serviceInvoke = retrofit.create(IServiceInvoke.class);
        }

        this.invoke = this.serviceInvoke.GetListDevice(order);

        this.invoke.enqueue(new Callback<ResponseDevice>() {
            @Override
            public void onResponse(Call<ResponseDevice> call, Response<ResponseDevice> response) {
                if (response != null && response.isSuccessful())
                {
                    final ResponseDevice responseDevice = response.body();
                    interactor.ShowListDevices(responseDevice.getObjects());
                }
                else
                {
                    interactor.Error(ErrorType.Fail, activity.getString(R.string.message_fail_download_info));
                }
            }

            @Override
            public void onFailure(Call<ResponseDevice> call, Throwable t) {
                    interactor.Error(ErrorType.Fail, activity.getString(R.string.message_fail_download_info));
            }
        });
    }

}
