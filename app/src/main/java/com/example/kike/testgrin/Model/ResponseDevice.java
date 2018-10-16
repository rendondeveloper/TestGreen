package com.example.kike.testgrin.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponseDevice
{
    private String status;
    private Date datetime;
    private List<Device> objects = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public List<Device> getObjects() {
        return objects;
    }

    public void setObjects(List<Device> objects) {
        this.objects = objects;
    }
}
