package com.example.banlkdt;

import java.io.Serializable;

public class LinhKien implements Serializable {
    private String malk,mansxlk,tenlk,gia,sl;


    public String getMalk() {
        return malk;
    }

    public void setMalk(String malk) {
        this.malk = malk;
    }

    public String getTenlk() {
        return tenlk;
    }

    public void setTenlk(String tenlk) {
        this.tenlk = tenlk;
    }

    public String getMansxlk() {
        return mansxlk;
    }

    public void setMansxlk(String mansxlk) {
        this.mansxlk = mansxlk;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public LinhKien() {
    }

    public LinhKien(String mansxlk, String tenlk, String gia, String sl) {
        this.mansxlk = mansxlk;
        this.tenlk = tenlk;
        this.gia = gia;
        this.sl = sl;
    }

    public LinhKien(String malk, String mansxlk, String tenlk, String gia, String sl) {
        this.malk = malk;
        this.mansxlk = mansxlk;
        this.tenlk = tenlk;
        this.gia = gia;
        this.sl = sl;
    }

    @Override
    public String toString() {
        return "LinhKien{" +
                "malk='" + malk + '\'' +
                ", mansxlk='" + mansxlk + '\'' +
                ", tenlk='" + tenlk + '\'' +
                ", gia='" + gia + '\'' +
                ", sl=" + sl +
                '}';
    }
}

