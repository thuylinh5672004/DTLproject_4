package com.example.model;

public class DtlTaikhoan {
    private String dtlMaTK;
    private String dtlTenDangNhap;
    private String dtlMatKhau;
    private String dtlLoaiTK;
    private String dtlMaKH;
    private String dtlTrangThai;

    public DtlTaikhoan(String dtlMaTK, String dtlTenDangNhap, String dtlMatKhau, String dtlLoaiTK, String dtlMaKH, String dtlTrangThai) {
        this.dtlMaTK = dtlMaTK;
        this.dtlTenDangNhap = dtlTenDangNhap;
        this.dtlMatKhau = dtlMatKhau;
        this.dtlLoaiTK = dtlLoaiTK;
        this.dtlMaKH = dtlMaKH;
        this.dtlTrangThai = dtlTrangThai;
    }

    public String getDtlMaTK() { return dtlMaTK; }
    public String getDtlTenDangNhap() { return dtlTenDangNhap; }
    public String getDtlMatKhau() { return dtlMatKhau; }
    public String getDtlLoaiTK() { return dtlLoaiTK; }
    public String getDtlMaKH() { return dtlMaKH; }
    public String getDtlTrangThai() { return dtlTrangThai; }
}
