package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import com.example.model.DtlDBConnect;
/**
 * Servlet implementation class DtlRegisterServlet
 */
@WebServlet("/DtlRegisterServlet")
public class DtlRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = "user"; // Mặc định tài khoản mới là user

        String jsonResponse;

        try (Connection connection = DtlDBConnect.getConnection()) {
            // Kiểm tra xem tài khoản đã tồn tại chưa
            String checkUserQuery = "SELECT COUNT(*) FROM dtltaikhoan WHERE DtlTenDangNhap = ?";
            PreparedStatement checkStmt = connection.prepareStatement(checkUserQuery);
            checkStmt.setString(1, username);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                jsonResponse = "{\"success\": false, \"message\": \"Tên đăng nhập đã tồn tại!\"}";
            } else {
                // Chèn dữ liệu mới vào bảng
                String query = "INSERT INTO dtltaikhoan (DtlTenDangNhap, DtlMatKhau, DtlEmail, DtlLoaiTK) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                pstmt.setString(3, email);
                pstmt.setString(4, role);

                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    jsonResponse = "{\"success\": true, \"message\": \"Đăng ký thành công!\"}";
                } else {
                    jsonResponse = "{\"success\": false, \"message\": \"Đăng ký thất bại!\"}";
                }
            }
        } catch (Exception e) {
            jsonResponse = "{\"success\": false, \"message\": \"Lỗi hệ thống: " + e.getMessage() + "\"}";
        }

        out.print(jsonResponse);
        out.flush();
    }
}