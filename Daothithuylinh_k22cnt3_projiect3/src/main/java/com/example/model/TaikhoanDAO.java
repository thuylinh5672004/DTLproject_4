package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class TaikhoanDAO {
	public Taikhoan login(String username, String password) {
        Taikhoan tk = null;
        try (Connection conn = DBConnect.getConnection()) {
            String sql = "SELECT * FROM taikhoan WHERE TenDangNhap = ? AND MatKhau = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                tk = new Taikhoan(
                    rs.getInt("MaTK"),
                    rs.getString("TenDangNhap"),
                    rs.getString("MatKhau"),
                    rs.getString("LoaiTK"),
                    rs.getString("TrangThai")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tk;
    }
}