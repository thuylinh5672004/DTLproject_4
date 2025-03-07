package com.example.model;

public class DtlSanPham {
    private int dtlMaSP;
    private String dtlTenSP;
    private String dtlMoTa;
    private double dtlGia;
    private int dtlSoLuong;
    private int dtlMaDM;  // Mã danh mục
    private int dtlMaNCC; // Mã nhà cung cấp
    private String dtlHinhAnh;

    // Constructors
    public DtlSanPham(String dtlMaSP2, String dtlTenSP2, double dtlGia2, String dtlMoTa2, String dtlHinhAnh2) {
    }

    public DtlSanPham(int dtlMaSP, String dtlTenSP, String dtlMoTa, double dtlGia, int dtlSoLuong, int dtlMaDM, int dtlMaNCC, String dtlHinhAnh) {
        this.dtlMaSP = dtlMaSP;
        this.dtlTenSP = dtlTenSP;
        this.dtlMoTa = dtlMoTa;
        this.dtlGia = dtlGia;
        this.dtlSoLuong = dtlSoLuong;
        this.dtlMaDM = dtlMaDM;
        this.dtlMaNCC = dtlMaNCC;
        this.dtlHinhAnh = dtlHinhAnh;
    }

    // Getters and Setters
    public int getDtlMaSP() {
        return dtlMaSP;
    }

    public void setDtlMaSP(int dtlMaSP) {
        this.dtlMaSP = dtlMaSP;
    }

    public String getDtlTenSP() {
        return dtlTenSP;
    }

    public void setDtlTenSP(String dtlTenSP) {
        this.dtlTenSP = dtlTenSP;
    }

    public String getDtlMoTa() {
        return dtlMoTa;
    }

    public void setDtlMoTa(String dtlMoTa) {
        this.dtlMoTa = dtlMoTa;
    }

    public double getDtlGia() {
        return dtlGia;
    }

    public void setDtlGia(double dtlGia) {
        this.dtlGia = dtlGia;
    }

    public int getDtlSoLuong() {
        return dtlSoLuong;
    }

    public void setDtlSoLuong(int dtlSoLuong) {
        this.dtlSoLuong = dtlSoLuong;
    }

    public int getDtlMaDM() {
        return dtlMaDM;
    }

    public void setDtlMaDM(int dtlMaDM) {
        this.dtlMaDM = dtlMaDM;
    }

    public int getDtlMaNCC() {
        return dtlMaNCC;
    }

    public void setDtlMaNCC(int dtlMaNCC) {
        this.dtlMaNCC = dtlMaNCC;
    }

    public String getDtlHinhAnh() {
        return dtlHinhAnh;
    }

    public void setDtlHinhAnh(String dtlHinhAnh) {
        this.dtlHinhAnh = dtlHinhAnh;
    }

    public Object getDtlMaSPObj() {
        return null;
    }

	public Object getmaSP() {
		// TODO Auto-generated method stub
		return null;
	}
}
