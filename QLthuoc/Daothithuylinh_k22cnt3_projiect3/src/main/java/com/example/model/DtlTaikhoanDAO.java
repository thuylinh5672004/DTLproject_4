package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class DtlTaikhoanDAO {
	 public DtlTaikhoan login(String username, String password) {
	        DtlTaikhoan tk = null;
	        try (Connection conn = DtlDBConnect.getConnection()) {
	            String sql = "SELECT * FROM dtltaikhoan WHERE TenDangNhap = ? AND MatKhau = ? AND LoaiTK = 'Admin' AND TrangThai = 'Hoạt động'";
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, username);
	            stmt.setString(2, password);

	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                tk = new DtlTaikhoan(
	                    rs.getString("dtlMaTK"),
	                    rs.getString("dtlTenDangNhap"),
	                    rs.getString("dtlMatKhau"),
	                    rs.getString("dtlLoaiTK"),
	                    rs.getString("dtlMaKH"),
	                    rs.getString("dtlTrangThai")
	                );
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return tk;
	    }
	}