package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NmhTaiKhoanDAO {
	 private Connection connection;

	    public NmhTaiKhoanDAO() throws SQLException {
	        this.connection = NmhDBConnection.getConnection();
	    }

	    // Lấy danh sách tất cả tài khoản
	    public List<NmhTaiKhoan> getAllTaiKhoan() throws SQLException {
	        List<NmhTaiKhoan> danhSachTaiKhoan = new ArrayList<>();
	        String query = "SELECT * FROM nmhtaikhoan";
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        while (rs.next()) {
	            NmhTaiKhoan taiKhoan = new NmhTaiKhoan(
	                rs.getInt("nmhId"),
	                rs.getString("nmhUsername"),
	                rs.getString("nmhPassword"),
	                rs.getString("nmhRole")
	            );
	            danhSachTaiKhoan.add(taiKhoan);
	        }
	        return danhSachTaiKhoan;
	    }

	    // Lấy thông tin tài khoản theo ID
	    public NmhTaiKhoan getTaiKhoanById(int id) throws SQLException {
	        String query = "SELECT * FROM nmhtaikhoan WHERE nmhId = ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setInt(1, id);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return new NmhTaiKhoan(
	                rs.getInt("nmhId"),
	                rs.getString("nmhUsername"),
	                rs.getString("nmhPassword"),
	                rs.getString("nmhRole")
	            );
	        }
	        return null;
	    }

	    // Đăng nhập tài khoản
	    public NmhTaiKhoan login(String username, String password) throws SQLException {
	        String query = "SELECT * FROM nmhtaikhoan WHERE nmhUsername = ? AND nmhPassword = ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, username);
	        pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            return new NmhTaiKhoan(
	                rs.getInt("nmhId"),
	                rs.getString("nmhUsername"),
	                rs.getString("nmhPassword"),
	                rs.getString("nmhRole")
	            );
	        }
	        return null;
	    }

	    // Thêm tài khoản mới
	    public void insertTaiKhoan(NmhTaiKhoan taiKhoan) throws SQLException {
	        String query = "INSERT INTO nmhtaikhoan (nmhUsername, nmhPassword, nmhRole) VALUES (?, ?, ?)";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, taiKhoan.getNmhUsername());
	        pstmt.setString(2, taiKhoan.getNmhPassword());
	        pstmt.setString(3, taiKhoan.getNmhRole());
	        pstmt.executeUpdate();
	    }

	    // Cập nhật thông tin tài khoản
	    public void updateTaiKhoan(NmhTaiKhoan taiKhoan) throws SQLException {
	        String query = "UPDATE nmhtaikhoan SET nmhUsername = ?, nmhPassword = ?, nmhRole = ? WHERE nmhId = ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, taiKhoan.getNmhUsername());
	        pstmt.setString(2, taiKhoan.getNmhPassword());
	        pstmt.setString(3, taiKhoan.getNmhRole());
	        pstmt.setInt(4, taiKhoan.getNmhId());
	        pstmt.executeUpdate();
	    }

	    // Xóa tài khoản
	    public void deleteTaiKhoan(int id) throws SQLException {
	        String query = "DELETE FROM nmhtaikhoan WHERE nmhId = ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setInt(1, id);
	        pstmt.executeUpdate();
	    }
	}
