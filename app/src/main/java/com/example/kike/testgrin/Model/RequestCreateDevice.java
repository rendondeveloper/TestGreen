package com.example.kike.testgrin.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestCreateDevice
{
    @SerializedName("body")
    @Expose
    private Device body;

    public Device getBody() {
        return body;
    }

    public void setBody(Device body) {
        this.body = body;
    }
}
