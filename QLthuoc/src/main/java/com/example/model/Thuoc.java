package com.example.model;

public class Thuoc {
	private int id;
    private String tenThuoc;
    private String ngayNhap;
    private String loaiThuoc;
    private int soLuong;
    private double donGia;
    private String anhThuoc; // Thêm thuộc tính lưu đường dẫn ảnh

    // Constructors
    public Thuoc() {
    }

    public Thuoc(int id, String tenThuoc, String ngayNhap, String loaiThuoc, int soLuong, double donGia, String anhThuoc) {
        this.id = id;
        this.tenThuoc = tenThuoc;
        this.ngayNhap = ngayNhap;
        this.loaiThuoc = loaiThuoc;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.anhThuoc = anhThuoc;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getLoaiThuoc() {
        return loaiThuoc;
    }

    public void setLoaiThuoc(String loaiThuoc) {
        this.loaiThuoc = loaiThuoc;
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

    public String getAnhThuoc() {
        return anhThuoc;
    }

    public void setAnhThuoc(String anhThuoc) {
        this.anhThuoc = anhThuoc;
    }
}