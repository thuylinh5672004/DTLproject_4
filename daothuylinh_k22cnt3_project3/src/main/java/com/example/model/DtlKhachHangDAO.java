package com.example.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class DtlKhachHangDAO {
    // Lấy danh sách tất cả khách hàng
	 public List<DtlKhachHang> getAllKhachHang() {
	        List<DtlKhachHang> list = new ArrayList<>();
	        try (Connection connection = DtlDBConnect.getConnection()) {
	            String sql = "SELECT * FROM dtlkhachhang";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                DtlKhachHang kh = new DtlKhachHang();
	                kh.setDtlMaKH(resultSet.getString("DtlMaKH"));
	                kh.setDtlHoTen(resultSet.getString("DtlHoTen"));
	                kh.setDtlEmail(resultSet.getString("DtlEmail"));
	                kh.setDtlSoDienThoai(resultSet.getString("DtlSoDienThoai"));
	                kh.setDtlDiaChi(resultSet.getString("DtlDiaChi"));

	                Timestamp ngayTao = resultSet.getTimestamp("DtlNgayTao");
	                kh.setDtlNgayTao(ngayTao != null ? ngayTao : new Timestamp(System.currentTimeMillis()));

	                list.add(kh);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	    // Lấy thông tin khách hàng theo mã khách hàng
	    public DtlKhachHang getKhachHangById(String DtlMaKH) {
	        DtlKhachHang kh = null;
	        try (Connection connection = DtlDBConnect.getConnection()) {
	            String sql = "SELECT * FROM dtlkhachhang WHERE DtlMaKH = ?";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, DtlMaKH);
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                kh = new DtlKhachHang();
	                kh.setDtlMaKH(resultSet.getString("DtlMaKH"));
	                kh.setDtlHoTen(resultSet.getString("DtlHoTen"));
	                kh.setDtlEmail(resultSet.getString("DtlEmail"));
	                kh.setDtlSoDienThoai(resultSet.getString("DtlSoDienThoai"));
	                kh.setDtlDiaChi(resultSet.getString("DtlDiaChi"));

	                Timestamp ngayTao = resultSet.getTimestamp("DtlNgayTao");
	                kh.setDtlNgayTao(ngayTao != null ? ngayTao : new Timestamp(System.currentTimeMillis()));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return kh;
	    }

	    // Thêm khách hàng mới
	    public boolean insertKhachHang(DtlKhachHang kh) {
	        try (Connection connection = DtlDBConnect.getConnection()) {
	            String sql = "INSERT INTO dtlkhachhang (DtlMaKH, DtlHoTen, DtlEmail, DtlSoDienThoai, DtlDiaChi, DtlNgayTao) VALUES (?, ?, ?, ?, ?, ?)";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, kh.getDtlMaKH());
	            statement.setString(2, kh.getDtlHoTen());
	            statement.setString(3, kh.getDtlEmail());
	            statement.setString(4, kh.getDtlSoDienThoai());
	            statement.setString(5, kh.getDtlDiaChi());
	            statement.setTimestamp(6, kh.getDtlNgayTao() != null ? kh.getDtlNgayTao() : new Timestamp(System.currentTimeMillis()));
	            return statement.executeUpdate() > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    // Cập nhật thông tin khách hàng
	    public boolean updateKhachHang(DtlKhachHang kh) {
	        try (Connection connection = DtlDBConnect.getConnection()) {
	            String sql = "UPDATE dtlkhachhang SET DtlHoTen = ?, DtlEmail = ?, DtlSoDienThoai = ?, DtlDiaChi = ?, DtlNgayTao = ? WHERE DtlMaKH = ?";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, kh.getDtlHoTen());
	            statement.setString(2, kh.getDtlEmail());
	            statement.setString(3, kh.getDtlSoDienThoai());
	            statement.setString(4, kh.getDtlDiaChi());
	            statement.setTimestamp(5, kh.getDtlNgayTao() != null ? kh.getDtlNgayTao() : new Timestamp(System.currentTimeMillis()));
	            statement.setString(6, kh.getDtlMaKH());
	            return statement.executeUpdate() > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }

	    // Xóa khách hàng theo mã khách hàng
	    public boolean deleteKhachHang(String DtlMaKH) {
	        try (Connection connection = DtlDBConnect.getConnection()) {
	            String sql = "DELETE FROM dtlkhachhang WHERE DtlMaKH = ?";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, DtlMaKH);
	            return statement.executeUpdate() > 0;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	}