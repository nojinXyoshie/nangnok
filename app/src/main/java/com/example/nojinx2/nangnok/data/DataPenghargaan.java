package com.example.nojinx2.nangnok.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataPenghargaan implements Serializable {

    @com.google.gson.annotations.SerializedName("name_achievement")
    private String name_achievement;

    public String getName_achievement() {
        return name_achievement;
    }

    public void setName_achievement(String name_achievement) {
        this.name_achievement = name_achievement;
    }


}
