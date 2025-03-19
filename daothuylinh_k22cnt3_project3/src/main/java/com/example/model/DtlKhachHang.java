package com.example.model;

import java.sql.Timestamp;

public class DtlKhachHang {
    private String DtlMaKH;
    private String DtlHoTen;
    private String DtlEmail;
    private String DtlSoDienThoai;
    private String DtlDiaChi;
    private Timestamp DtlNgayTao;

    // Constructor không tham số
    public DtlKhachHang() {
    }

    // Constructor đầy đủ tham số
    public DtlKhachHang(String DtlMaKH, String DtlHoTen, String DtlEmail, String DtlSoDienThoai, String DtlDiaChi, Timestamp DtlNgayTao) {
        this.DtlMaKH = DtlMaKH;
        this.DtlHoTen = DtlHoTen;
        this.DtlEmail = DtlEmail;
        this.DtlSoDienThoai = DtlSoDienThoai;
        this.DtlDiaChi = DtlDiaChi;
        this.DtlNgayTao = DtlNgayTao;
    }

    // Getter và Setter
    public String getDtlMaKH() {
        return DtlMaKH;
    }

    public void setDtlMaKH(String DtlMaKH) {
        this.DtlMaKH = DtlMaKH;
    }

    public String getDtlHoTen() {
        return DtlHoTen;
    }

    public void setDtlHoTen(String DtlHoTen) {
        this.DtlHoTen = DtlHoTen;
    }

    public String getDtlEmail() {
        return DtlEmail;
    }

    public void setDtlEmail(String DtlEmail) {
        this.DtlEmail = DtlEmail;
    }

    public String getDtlSoDienThoai() {
        return DtlSoDienThoai;
    }

    public void setDtlSoDienThoai(String DtlSoDienThoai) {
        this.DtlSoDienThoai = DtlSoDienThoai;
    }

    public String getDtlDiaChi() {
        return DtlDiaChi;
    }

    public void setDtlDiaChi(String DtlDiaChi) {
        this.DtlDiaChi = DtlDiaChi;
    }

    public Timestamp getDtlNgayTao() {
        return DtlNgayTao;
    }

    public void setDtlNgayTao(Timestamp DtlNgayTao) {
        this.DtlNgayTao = DtlNgayTao;
    }

    // Phương thức toString() để debug nhanh
    @Override
    public String toString() {
        return "DtlKhachHang{" +
                "DtlMaKH='" + DtlMaKH + '\'' +
                ", DtlHoTen='" + DtlHoTen + '\'' +
                ", DtlEmail='" + DtlEmail + '\'' +
                ", DtlSoDienThoai='" + DtlSoDienThoai + '\'' +
                ", DtlDiaChi='" + DtlDiaChi + '\'' +
                ", DtlNgayTao=" + DtlNgayTao +
                '}';
    }
}
