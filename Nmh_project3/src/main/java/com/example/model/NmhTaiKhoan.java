package com.example.model;

public class NmhTaiKhoan {
	 private int nmhId;
	    private String nmhUsername;
	    private String nmhPassword;
	    private String nmhRole;

	    public NmhTaiKhoan(int nmhId, String nmhUsername, String nmhPassword, String nmhRole) {
	        this.nmhId = nmhId;
	        this.nmhUsername = nmhUsername;
	        this.nmhPassword = nmhPassword;
	        this.nmhRole = nmhRole;
	    }

	    public int getNmhId() { return nmhId; }
	    public void setNmhId(int nmhId) { this.nmhId = nmhId; }

	    public String getNmhUsername() { return nmhUsername; }
	    public void setNmhUsername(String nmhUsername) { this.nmhUsername = nmhUsername; }

	    public String getNmhPassword() { return nmhPassword; }
	    public void setNmhPassword(String nmhPassword) { this.nmhPassword = nmhPassword; }

	    public String getNmhRole() { return nmhRole; }
	    public void setNmhRole(String nmhRole) { this.nmhRole = nmhRole; }
	}