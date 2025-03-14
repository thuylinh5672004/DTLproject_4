package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NmhKhachHangDAO {
	private Connection connection;

    public NmhKhachHangDAO() {
        try {
            this.connection = NmhDBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🟢 Lấy tất cả khách hàng
    public List<NmhKhachHang> getAllKhachHang() throws SQLException {
        List<NmhKhachHang> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM nmhkhachhang";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                NmhKhachHang kh = new NmhKhachHang(
                        rs.getInt("nmhId"),
                        rs.getString("nmhTen"),
                        rs.getString("nmhSdt"),
                        rs.getString("nmhEmail")
                );
                danhSach.add(kh);
            }
        }
        return danhSach;
    }

    // 🟢 Lấy khách hàng theo ID
    public NmhKhachHang getKhachHangById(int id) throws SQLException {
        String sql = "SELECT * FROM nmhkhachhang WHERE nmhId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new NmhKhachHang(
                            rs.getInt("nmhId"),
                            rs.getString("nmhTen"),
                            rs.getString("nmhSdt"),
                            rs.getString("nmhEmail")
                    );
                }
            }
        }
        return null;
    }

    // 🟢 Thêm mới khách hàng
    public void insertKhachHang(NmhKhachHang khachHang) throws SQLException {
        String sql = "INSERT INTO nmhkhachhang (nmhTen, nmhSdt, nmhEmail) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, khachHang.getNmhTen());
            stmt.setString(2, khachHang.getNmhSdt());
            stmt.setString(3, khachHang.getNmhEmail());
            stmt.executeUpdate();
        }
    }

    // 🟢 Cập nhật thông tin khách hàng
    public void updateKhachHang(NmhKhachHang khachHang) throws SQLException {
        String sql = "UPDATE nmhkhachhang SET nmhTen = ?, nmhSdt = ?, nmhEmail = ? WHERE nmhId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, khachHang.getNmhTen());
            stmt.setString(2, khachHang.getNmhSdt());
            stmt.setString(3, khachHang.getNmhEmail());
            stmt.setInt(4, khachHang.getNmhId());
            stmt.executeUpdate();
        }
    }

    // 🟢 Xóa khách hàng theo ID
    public void deleteKhachHang(int id) throws SQLException {
        String sql = "DELETE FROM nmhkhachhang WHERE nmhId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}