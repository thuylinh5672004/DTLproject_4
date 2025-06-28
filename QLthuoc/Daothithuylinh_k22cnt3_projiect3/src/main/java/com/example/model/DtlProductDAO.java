package com.example.model;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DtlProductDAO {
    
    // Lấy danh sách tất cả sản phẩm
    public List<DtlProduct> getAllProducts() {
        List<DtlProduct> dtlProducts = new ArrayList<>();
        String sql = "SELECT * FROM products";

        try (Connection conn = DtlDBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DtlProduct dtlProduct = new DtlProduct(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price")
                );
                dtlProducts.add(dtlProduct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtlProducts;
    }

    // Thêm sản phẩm mới
    public void addDtlProduct(DtlProduct dtlProduct) {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";

        try (Connection conn = DtlDBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dtlProduct.getDtlName());
            pstmt.setDouble(2, dtlProduct.getDtlPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Cập nhật sản phẩm
    public void updateDtlProduct(DtlProduct dtlProduct) {
        String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";

        try (Connection conn = DtlDBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dtlProduct.getDtlName());
            pstmt.setDouble(2, dtlProduct.getDtlPrice());
            pstmt.setInt(3, dtlProduct.getDtlId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Xóa sản phẩm theo ID
    public void deleteDtlProduct(int dtlId) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DtlDBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, dtlId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lấy sản phẩm theo ID
    public DtlProduct getDtlProductById(int dtlId) {
        String sql = "SELECT * FROM products WHERE id = ?";
        DtlProduct dtlProduct = null;

        try (Connection conn = DtlDBConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, dtlId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                dtlProduct = new DtlProduct(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtlProduct;
    }
}
