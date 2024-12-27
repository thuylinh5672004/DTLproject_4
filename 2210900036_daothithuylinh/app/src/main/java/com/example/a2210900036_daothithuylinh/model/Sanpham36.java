package com.example.a2210900036_daothithuylinh.model;

public class Sanpham36 {
    private String maSP;
    private String tenSP;
    private int soLuong;
    private double donGia;

    public Sanpham36() {
    }

    public Sanpham36(String maSP, String tenSP, int soLuong, double donGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }
    public double tinhThanhTien() {
        double thanhTien = soLuong * donGia;
        if (soLuong > 10) {
            thanhTien *= 0.9; // Giảm 10%
        }
        return thanhTien;
    }


    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }
    public String toString(){
        return maSP+"-"+tenSP+"-"+soLuong+"_"+donGia;
    }


}
