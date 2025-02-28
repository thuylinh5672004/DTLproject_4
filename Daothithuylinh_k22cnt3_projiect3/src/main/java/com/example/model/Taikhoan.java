package com.example.model;



public class Taikhoan {
    private int maTK;
    private String tenDangNhap;
    private String matKhau;
    private String loaiTK;
    private String trangThai;

    public Taikhoan(int maTK, String tenDangNhap, String matKhau, String loaiTK, String trangThai) {
        this.maTK = maTK;
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
        this.loaiTK = loaiTK;
        this.trangThai = trangThai;
    }

    public int getMaTK() { return maTK; }
    public String getTenDangNhap() { return tenDangNhap; }
    public String getMatKhau() { return matKhau; }
    public String getLoaiTK() { return loaiTK; }
    public String getTrangThai() { return trangThai; }
}
