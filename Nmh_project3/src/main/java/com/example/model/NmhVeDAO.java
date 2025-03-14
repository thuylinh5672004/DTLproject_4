package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NmhVeDAO {
	 public boolean insertBaiDoXe(NmhBaiDoXe baiDoXe) {
	        String sql = "INSERT INTO nmhbaidoxe (nmhTenBai, nmhSucChua, nmhDiaChi) VALUES (?, ?, ?)";
	        try (Connection conn = NmhDBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, baiDoXe.getNmhTenBai());
	            stmt.setInt(2, baiDoXe.getNmhSucChua());
	            stmt.setString(3, baiDoXe.getNmhDiaChi());
	            return stmt.executeUpdate() > 0; // Trả về true nếu thêm thành công
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    // Cập nhật thông tin bãi đỗ xe
	    public boolean updateBaiDoXe(NmhBaiDoXe baiDoXe) {
	        String sql = "UPDATE nmhbaidoxe SET nmhTenBai = ?, nmhSucChua = ?, nmhDiaChi = ? WHERE nmhId = ?";
	        try (Connection conn = NmhDBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, baiDoXe.getNmhTenBai());
	            stmt.setInt(2, baiDoXe.getNmhSucChua());
	            stmt.setString(3, baiDoXe.getNmhDiaChi());
	            stmt.setInt(4, baiDoXe.getNmhId());
	            return stmt.executeUpdate() > 0; // Trả về true nếu cập nhật thành công
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    // Xóa bãi đỗ xe theo ID
	    public boolean deleteBaiDoXe(int id) {
	        String sql = "DELETE FROM nmhbaidoxe WHERE nmhId = ?";
	        try (Connection conn = NmhDBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            return stmt.executeUpdate() > 0; // Trả về true nếu xóa thành công
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    // Lấy danh sách tất cả bãi đỗ xe
	    public List<NmhBaiDoXe> getAllBaiDoXe() {
	        List<NmhBaiDoXe> list = new ArrayList<>();
	        String sql = "SELECT * FROM nmhbaidoxe";
	        try (Connection conn = NmhDBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                list.add(new NmhBaiDoXe(
	                    rs.getInt("nmhId"),
	                    rs.getString("nmhTenBai"),
	                    rs.getInt("nmhSucChua"),
	                    rs.getString("nmhDiaChi")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	    // Tìm bãi đỗ xe theo ID
	    public NmhBaiDoXe getBaiDoXeById(int id) {
	        String sql = "SELECT * FROM nmhbaidoxe WHERE nmhId = ?";
	        try (Connection conn = NmhDBConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    return new NmhBaiDoXe(
	                        rs.getInt("nmhId"),
	                        rs.getString("nmhTenBai"),
	                        rs.getInt("nmhSucChua"),
	                        rs.getString("nmhDiaChi")
	                    );
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }


		public List<NmhVe> getAllVe() {
			// TODO Auto-generated method stub
			return null;
		}

		public void insertVe(NmhVe nmhVe) {
			// TODO Auto-generated method stub
			
		}
	}
