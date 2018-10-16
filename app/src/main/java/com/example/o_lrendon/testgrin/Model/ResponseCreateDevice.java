package com.example.o_lrendon.testgrin.Model;

import java.sql.Date;

public class ResponseCreateDevice
{
    private String status;
    private Date datetime;
    private Device object;

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

    public Device getObject() {
        return object;
    }

    public void setObject(Device object) {
        this.object = object;
    }
}
