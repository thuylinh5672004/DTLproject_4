package com.example.model;

public class SanPham {
	private int maSP;
    private String tenSP;
    private String moTa;
    private double gia;
    private int soLuong;
    private int maDM;  // Mã danh mục
    private int maNCC; // Mã nhà cung cấp
    private String hinhAnh;

    // Constructors
    public SanPham() {
    }

    public SanPham(int maSP, String tenSP, String moTa, double gia, int soLuong, int maDM, int maNCC, String hinhAnh) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.moTa = moTa;
        this.gia = gia;
        this.soLuong = soLuong;
        this.maDM = maDM;
        this.maNCC = maNCC;
        this.hinhAnh = hinhAnh;
    }

    // Getters and Setters
    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaDM() {
        return maDM;
    }

    public void setMaDM(int maDM) {
        this.maDM = maDM;
    }

    public int getMaNCC() {
        return maNCC;
    }

    public void setMaNCC(int maNCC) {
        this.maNCC = maNCC;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

	

	public Object getmaSP() {
		// TODO Auto-generated method stub
		return null;
	}
}
