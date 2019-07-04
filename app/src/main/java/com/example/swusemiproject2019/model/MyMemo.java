package com.example.swusemiproject2019.model;

import java.io.Serializable;

public class MyMemo implements Serializable {
    private int imgId;
    private String memo;
    private String date;

    public MyMemo() {}
    public MyMemo(int imgId, String memo, String date) {
        this.imgId = imgId;
        this.memo = memo;
        this.date = date;
    }

    @Override
    public String toString() {
        return "MyMemo{" +
                "imgId=" + imgId +
                ", memo='" + memo + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
