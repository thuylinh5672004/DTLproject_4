package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NmhBaiDoXeDAO {
	 private Connection conn;

	    public NmhBaiDoXeDAO() {
	        try {
	            conn = NmhDBConnection.getConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Lấy tất cả bãi đỗ xe
	    public List<NmhBaiDoXe> getAllBaiDoXe() {
	        List<NmhBaiDoXe> list = new ArrayList<>();
	        String sql = "SELECT * FROM nmhbaidoxe";

	        try (PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                int nmhId = rs.getInt("id");
	                String nmhTenBai = rs.getString("ten_bai");
	                int nmhSucChua = rs.getInt("suc_chua");
	                String nmhDiaChi = rs.getString("dia_chi");

	                list.add(new NmhBaiDoXe(nmhId, nmhTenBai, nmhSucChua, nmhDiaChi));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	    // Thêm bãi đỗ xe
	    public void insertBaiDoXe(NmhBaiDoXe nmhBaiDoXe) {
	        String sql = "INSERT INTO nmhbaidoxe (ten_bai, suc_chua, dia_chi) VALUES (?, ?, ?)";

	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, nmhBaiDoXe.getNmhTenBai());
	            ps.setInt(2, nmhBaiDoXe.getNmhSucChua());
	            ps.setString(3, nmhBaiDoXe.getNmhDiaChi());

	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Cập nhật bãi đỗ xe
	    public void updateBaiDoXe(NmhBaiDoXe nmhBaiDoXe) {
	        String sql = "UPDATE nmhbaidoxe SET ten_bai = ?, suc_chua = ?, dia_chi = ? WHERE id = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setString(1, nmhBaiDoXe.getNmhTenBai());
	            ps.setInt(2, nmhBaiDoXe.getNmhSucChua());
	            ps.setString(3, nmhBaiDoXe.getNmhDiaChi());
	            ps.setInt(4, nmhBaiDoXe.getNmhId());

	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Xóa bãi đỗ xe theo ID
	    public void deleteBaiDoXe(int nmhId) {
	        String sql = "DELETE FROM nmhbaidoxe WHERE id = ?";

	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, nmhId);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    // Lấy thông tin bãi đỗ xe theo ID
	    public NmhBaiDoXe getBaiDoXeById(int nmhId) {
	        String sql = "SELECT * FROM nmhbaidoxe WHERE id = ?";
	        NmhBaiDoXe nmhBaiDoXe = null;

	        try (PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, nmhId);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                String nmhTenBai = rs.getString("ten_bai");
	                int nmhSucChua = rs.getInt("suc_chua");
	                String nmhDiaChi = rs.getString("dia_chi");

	                nmhBaiDoXe = new NmhBaiDoXe(nmhId, nmhTenBai, nmhSucChua, nmhDiaChi);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return nmhBaiDoXe;
	    }
	}