package com.example.model;

public class NmhKhachHang {
	 private int nmhId;
	    private String nmhTen;
	    private String nmhSdt;
	    private String nmhEmail;

	    public NmhKhachHang(int nmhId, String nmhTen, String nmhSdt, String nmhEmail) {
	        this.nmhId = nmhId;
	        this.nmhTen = nmhTen;
	        this.nmhSdt = nmhSdt;
	        this.nmhEmail = nmhEmail;
	    }

	    public int getNmhId() { return nmhId; }
	    public void setNmhId(int nmhId) { this.nmhId = nmhId; }

	    public String getNmhTen() { return nmhTen; }
	    public void setNmhTen(String nmhTen) { this.nmhTen = nmhTen; }

	    public String getNmhSdt() { return nmhSdt; }
	    public void setNmhSdt(String nmhSdt) { this.nmhSdt = nmhSdt; }

	    public String getNmhEmail() { return nmhEmail; }
	    public void setNmhEmail(String nmhEmail) { this.nmhEmail = nmhEmail; }
	}