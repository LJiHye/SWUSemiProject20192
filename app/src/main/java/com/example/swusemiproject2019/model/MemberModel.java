package com.example.swusemiproject2019.model;

import android.content.SharedPreferences;

import java.io.Serializable;

public class MemberModel implements Serializable {
    private static SharedPreferences sf;

    private String id;
    private String pwd;
    private String name;
    private int profile;

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", profile=" + profile +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }


}
