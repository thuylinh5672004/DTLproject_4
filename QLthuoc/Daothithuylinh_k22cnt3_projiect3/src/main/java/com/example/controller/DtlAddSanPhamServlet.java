package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.example.model.DtlDBConnect;

/**
 * Servlet implementation class DtlAddSanPhamServlet
 */
@WebServlet("/DtlAddSanPhamServlet")
public class DtlAddSanPhamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Xử lý yêu cầu GET (chuyển hướng đến trang thêm sản phẩm)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("Dtladd_product.html"); // Trang giao diện thêm sản phẩm
    }

    // Xử lý yêu cầu POST (thêm sản phẩm vào cơ sở dữ liệu)
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy dữ liệu từ form
        String dtlMaSP = request.getParameter("dtlMaSP");
        String dtlTenSP = request.getParameter("dtlTenSP");
        String dtlMoTa = request.getParameter("dtlMoTa");
        String dtlGiaStr = request.getParameter("dtlGia");
        String dtlSoLuongStr = request.getParameter("dtlSoLuong");
        String dtlMaDM = request.getParameter("dtlMaDM");
        String dtlMaNCC = request.getParameter("dtlMaNCC");
        String dtlHinhAnh = request.getParameter("dtlHinhAnh");

        // Kiểm tra và chuyển đổi dữ liệu
        int dtlSoLuong = 0;
        double dtlGia = 0.0;
        try {
            dtlSoLuong = Integer.parseInt(dtlSoLuongStr);
            dtlGia = Double.parseDouble(dtlGiaStr);
        } catch (NumberFormatException e) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h3>Error: Giá hoặc số lượng không hợp lệ!</h3>");
            return;
        }

        try (Connection connection = DtlDBConnect.getConnection()) {
            // Câu lệnh SQL để thêm sản phẩm mới vào cơ sở dữ liệu
            String query = "INSERT INTO dtlsanpham (dtlMaSP, dtlTenSP, dtlMoTa, dtlGia, dtlSoLuong, dtlMaDM, dtlMaNCC, dtlHinhAnh) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, dtlMaSP);
            statement.setString(2, dtlTenSP);
            statement.setString(3, dtlMoTa);
            statement.setDouble(4, dtlGia);
            statement.setInt(5, dtlSoLuong);
            statement.setString(6, dtlMaDM);
            statement.setString(7, dtlMaNCC);
            statement.setString(8, dtlHinhAnh);

            // Thực thi lệnh SQL
            statement.executeUpdate();

            // Chuyển hướng về danh sách sản phẩm sau khi thêm thành công
            response.sendRedirect("DtlProductServlet");
        } catch (Exception e) {
            // Hiển thị lỗi nếu xảy ra vấn đề
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace(response.getWriter());
        }
    }
}
