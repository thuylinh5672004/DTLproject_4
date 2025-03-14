package com.example.model;

public class DtlTaikhoan {
	 private String dtlTenDangNhap;
	    private String dtlMatKhau;
	    private String dtlLoaiTK;

	    // Constructor không tham số
	    public DtlTaikhoan() {
	    }

	    // Constructor có tham số
	    public DtlTaikhoan(String dtlTenDangNhap, String dtlMatKhau, String dtlLoaiTK) {
	        this.dtlTenDangNhap = dtlTenDangNhap;
	        this.dtlMatKhau = dtlMatKhau;
	        this.dtlLoaiTK = dtlLoaiTK;
	    }

	    // Getter & Setter
	    public String getDtlTenDangNhap() {
	        return dtlTenDangNhap;
	    }

	    public void setDtlTenDangNhap(String dtlTenDangNhap) {
	        this.dtlTenDangNhap = dtlTenDangNhap;
	    }

	    public String getDtlMatKhau() {
	        return dtlMatKhau;
	    }

	    public void setDtlMatKhau(String dtlMatKhau) {
	        this.dtlMatKhau = dtlMatKhau;
	    }

	    public String getDtlLoaiTK() {
	        return dtlLoaiTK;
	    }

	    public void setDtlLoaiTK(String dtlLoaiTK) {
	        this.dtlLoaiTK = dtlLoaiTK;
	    }

	    @Override
	    public String toString() {
	        return "DtlTaikhoan{" +
	                "dtlTenDangNhap='" + dtlTenDangNhap + '\'' +
	                ", dtlLoaiTK='" + dtlLoaiTK + '\'' +
	                '}';
	    }
	}
