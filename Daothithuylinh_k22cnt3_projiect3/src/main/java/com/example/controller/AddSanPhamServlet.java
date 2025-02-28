package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.example.model.DBConnect;
/**
 * Servlet implementation class AddSanPhamServlet
 */
@WebServlet("/AddSanPhamServlet")
public class AddSanPhamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    // Xử lý yêu cầu GET (chuyển hướng đến trang thêm sản phẩm)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("addProduct.html"); // Trang giao diện thêm sản phẩm
    }

    // Xử lý yêu cầu POST (thêm sản phẩm vào cơ sở dữ liệu)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String maSP = request.getParameter("maSP");
        String tenSP = request.getParameter("tenSP");
        String moTa = request.getParameter("moTa");
        String giaStr = request.getParameter("gia");
        String soLuongStr = request.getParameter("soLuong");
        String maDM = request.getParameter("maDM");
        String maNCC = request.getParameter("maNCC");
        String hinhAnh = request.getParameter("hinhAnh");

        // Kiểm tra và chuyển đổi dữ liệu
        int soLuong = 0;
        double gia = 0.0;
        try {
            soLuong = Integer.parseInt(soLuongStr);
            gia = Double.parseDouble(giaStr);
        } catch (NumberFormatException e) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h3>Error: Giá hoặc số lượng không hợp lệ!</h3>");
            return;
        }

        try (Connection connection = DBConnect.getConnection()) {
            // Câu lệnh SQL để thêm sản phẩm mới vào cơ sở dữ liệu
            String query = "INSERT INTO sanpham (MaSP, TenSP, MoTa, Gia, SoLuong, MaDM, MaNCC, HinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, maSP);
            statement.setString(2, tenSP);
            statement.setString(3, moTa);
            statement.setDouble(4, gia);
            statement.setInt(5, soLuong);
            statement.setString(6, maDM);
            statement.setString(7, maNCC);
            statement.setString(8, hinhAnh);

            // Thực thi lệnh SQL
            statement.executeUpdate();

            // Chuyển hướng về danh sách sản phẩm sau khi thêm thành công
            response.sendRedirect("ProductServlet");
        } catch (Exception e) {
            // Hiển thị lỗi nếu xảy ra vấn đề
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace(response.getWriter());
        }
    }
}