package com.example.model;

public class DtlProduct {
    private int dtlId;
    private String dtlName;
    private double dtlPrice;

    // Constructors
    public DtlProduct() {
    }

    public DtlProduct(String dtlName, double dtlPrice) {
        this.dtlName = dtlName;
        this.dtlPrice = dtlPrice;
    }

    public DtlProduct(int dtlId, String dtlName, double dtlPrice) {
        this.dtlId = dtlId;
        this.dtlName = dtlName;
        this.dtlPrice = dtlPrice;
    }

    // Getters và Setters
    public int getDtlId() {
        return dtlId;
    }

    public void setDtlId(int dtlId) {
        this.dtlId = dtlId;
    }

    public String getDtlName() {
        return dtlName;
    }

    public void setDtlName(String dtlName) {
        this.dtlName = dtlName;
    }

    public double getDtlPrice() {
        return dtlPrice;
    }

    public void setDtlPrice(double dtlPrice) {
        this.dtlPrice = dtlPrice;
    }
}
