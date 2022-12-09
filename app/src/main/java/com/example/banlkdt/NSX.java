package com.example.banlkdt;
import java.io.Serializable;

public class NSX implements Serializable {
    private String mansx;
    private String TenNSX;
    private String DiaChi;
    private String SDT;

    public String getmansx() {
        return mansx;
    }

    public void setmansx(String mansx) {
        mansx = mansx;
    }

    public String getTenNSX() {
        return TenNSX;
    }

    public void setTenNSX(String tenNSX) {
        TenNSX = tenNSX;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public NSX() {
    }

    public NSX( String tenNSX, String diaChi, String SDT) {
        TenNSX = tenNSX;
        DiaChi = diaChi;
        this.SDT = SDT;
    }

    public NSX(String mansx, String tenNSX, String diaChi, String SDT) {
        this.mansx = mansx;
        TenNSX = tenNSX;
        DiaChi = diaChi;
        this.SDT = SDT;
    }

    @Override
    public String toString() {
        return "NSX{" +
                "mansx='" + mansx + '\'' +
                ", TenNSX='" + TenNSX + '\'' +
                ", DiaChi='" + DiaChi + '\'' +
                ", SDT='" + SDT + '\'' +
                '}';
    }
}

