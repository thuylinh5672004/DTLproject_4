package com.example.model;

public class NmhBaiDoXe {
	private int nmhId;
    private String nmhTenBai;
    private int nmhSucChua;
    private String nmhDiaChi;

    public NmhBaiDoXe(int nmhId, String nmhTenBai, int nmhSucChua, String nmhDiaChi) {
        this.nmhId = nmhId;
        this.nmhTenBai = nmhTenBai;
        this.nmhSucChua = nmhSucChua;
        this.nmhDiaChi = nmhDiaChi;
    }

    public int getNmhId() { return nmhId; }
    public void setNmhId(int nmhId) { this.nmhId = nmhId; }

    public String getNmhTenBai() { return nmhTenBai; }
    public void setNmhTenBai(String nmhTenBai) { this.nmhTenBai = nmhTenBai; }

    public int getNmhSucChua() { return nmhSucChua; }
    public void setNmhSucChua(int nmhSucChua) { this.nmhSucChua = nmhSucChua; }

    public String getNmhDiaChi() { return nmhDiaChi; }
    public void setNmhDiaChi(String nmhDiaChi) { this.nmhDiaChi = nmhDiaChi; }
}
