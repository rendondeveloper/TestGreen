package com.example.o_lrendon.testgrin.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Device
{
    private int position;
    private String name;
    private String strength;
    private Date created;

    public Device() {
    }

    public Device(int position, String name, String strength, Date created) {
        this.position = position;
        this.name = name;
        this.strength = strength;
        this.created = created;
    }

    public Device(String name, String strength) {
        this.name = name;
        this.strength = strength;
    }

    public Device(String name, String strength, Date created) {
        this.name = name;
        this.strength = strength;
        this.created = created;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String intensity) {
        this.strength = intensity;
    }
}
