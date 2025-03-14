package com.example.model;

import java.security.Timestamp;

public class NmhVe {
	 private int nmhId;
	    private String nmhBienSo;
	    private String nmhLoaiXe;
	    private Timestamp nmhThoiGianVao;
	    private Timestamp nmhThoiGianRa;
	    private String nmhTrangThai;
	    private double nmhPhiGuiXe;

	    public NmhVe(int nmhId, String nmhBienSo, String nmhLoaiXe, Timestamp nmhThoiGianVao, Timestamp nmhThoiGianRa, String nmhTrangThai, double nmhPhiGuiXe) {
	        this.nmhId = nmhId;
	        this.nmhBienSo = nmhBienSo;
	        this.nmhLoaiXe = nmhLoaiXe;
	        this.nmhThoiGianVao = nmhThoiGianVao;
	        this.nmhThoiGianRa = nmhThoiGianRa;
	        this.nmhTrangThai = nmhTrangThai;
	        this.nmhPhiGuiXe = nmhPhiGuiXe;
	    }

	    public int getNmhId() { return nmhId; }
	    public void setNmhId(int nmhId) { this.nmhId = nmhId; }

	    public String getNmhBienSo() { return nmhBienSo; }
	    public void setNmhBienSo(String nmhBienSo) { this.nmhBienSo = nmhBienSo; }

	    public String getNmhLoaiXe() { return nmhLoaiXe; }
	    public void setNmhLoaiXe(String nmhLoaiXe) { this.nmhLoaiXe = nmhLoaiXe; }

	    public Timestamp getNmhThoiGianVao() { return nmhThoiGianVao; }
	    public void setNmhThoiGianVao(Timestamp nmhThoiGianVao) { this.nmhThoiGianVao = nmhThoiGianVao; }

	    public Timestamp getNmhThoiGianRa() { return nmhThoiGianRa; }
	    public void setNmhThoiGianRa(Timestamp nmhThoiGianRa) { this.nmhThoiGianRa = nmhThoiGianRa; }

	    public String getNmhTrangThai() { return nmhTrangThai; }
	    public void setNmhTrangThai(String nmhTrangThai) { this.nmhTrangThai = nmhTrangThai; }

	    public double getNmhPhiGuiXe() { return nmhPhiGuiXe; }
	    public void setNmhPhiGuiXe(double nmhPhiGuiXe) { this.nmhPhiGuiXe = nmhPhiGuiXe; }
	}
