package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DtlTaikhoanDAO {
	public DtlTaikhoan login(String username, String password) {
        DtlTaikhoan user = null;
        try (Connection connection = DtlDBConnect.getConnection()) {
            String sql = "SELECT * FROM dtltaikhoan WHERE DtlTenDangNhap = ? AND DtlMatKhau = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new DtlTaikhoan();
                user.setDtlTenDangNhap(resultSet.getString("DtlTenDangNhap"));
                user.setDtlLoaiTK(resultSet.getString("DtlLoaiTK"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
