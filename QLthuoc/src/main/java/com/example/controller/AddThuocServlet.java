package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.example.model.DBThuocDao;

import java.sql.*;

/**
 * Servlet implementation class AddThuocServlet
 */
@WebServlet("/AddThuocServlet")
public class AddThuocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    // Xử lý yêu cầu GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chuyển hướng người dùng tới trang thêm thuốc
        response.sendRedirect("addThuoc.html");
    }

    // Xử lý yêu cầu POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Nhận dữ liệu từ form
        String tenThuoc = request.getParameter("tenThuoc");
        String ngayNhap = request.getParameter("ngayNhap");
        String loaiThuoc = request.getParameter("loaiThuoc");
        String soLuongStr = request.getParameter("soLuong");
        String donGiaStr = request.getParameter("donGia");
        String anhThuoc = request.getParameter("anhThuoc");

        // Kiểm tra và chuyển đổi dữ liệu số lượng và đơn giá
        int soLuong = 0;
        double donGia = 0.0;
        try {
            soLuong = Integer.parseInt(soLuongStr);
            donGia = Double.parseDouble(donGiaStr);
        } catch (NumberFormatException e) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h3>Error: Số lượng hoặc đơn giá không hợp lệ!</h3>");
            return;
        }

        try (Connection connection = DBThuocDao.getConnection()) {
            // Thực hiện câu lệnh SQL để thêm thuốc mới
            String query = "INSERT INTO thuoc_daothithuylinh (TenThuoc, NgayNhap, LoaiThuoc, SoLuong, DonGia, AnhThuoc) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tenThuoc);
            statement.setString(2, ngayNhap);
            statement.setString(3, loaiThuoc);
            statement.setInt(4, soLuong);
            statement.setDouble(5, donGia);
            statement.setString(6, anhThuoc);

            statement.executeUpdate();

            // Chuyển hướng về danh sách thuốc sau khi thêm thành công
            response.sendRedirect("ThuocServlet");
        } catch (Exception e) {
            // Hiển thị lỗi nếu xảy ra
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace(response.getWriter());
        }
    }
}
