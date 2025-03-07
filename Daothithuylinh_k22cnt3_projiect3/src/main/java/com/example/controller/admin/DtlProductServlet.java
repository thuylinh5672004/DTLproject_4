package com.example.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;
import com.example.model.DtlDBConnect;

@WebServlet("/DtlProductServlet")
public class DtlProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        response.setContentType("text/html;charset=UTF-8");
        try (Connection connection = DtlDBConnect.getConnection();
             PrintWriter out = response.getWriter()) {

            // Bắt đầu trang HTML
            out.println("<!DOCTYPE html>");
            out.println("<html lang='vi'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Quản lý sản phẩm</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");

            // Thanh công cụ (Navbar)
            out.println("<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand' href='#'>Admin Panel</a>");
            out.println("<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav'>");
            out.println("<span class='navbar-toggler-icon'></span>");
            out.println("</button>");
            out.println("<div class='collapse navbar-collapse' id='navbarNav'>");
            out.println("<ul class='navbar-nav'>");
            out.println("<li class='nav-item'><a class='nav-link active' href='DtlProductServlet'>Quản lý sản phẩm</a></li>");
            out.println("<li class='nav-item'><a class='nav-link' href='DtlDashboardServlet'>Quản lý đơn hàng</a></li>");
            if (username == null) {
                out.println("<li class='nav-item'><a class='nav-link' href='login.html'>Đăng nhập</a></li>");
                out.println("<li class='nav-item'><a class='nav-link' href='register.html'>Đăng ký</a></li>");
            } else {
                out.println("<li class='nav-item'><a class='nav-link' href='DtlLogoutServlet'>Đăng xuất</a></li>");
            }
            out.println("</ul>");
            out.println("</div>");
            out.println("</div>");
            out.println("</nav>");

            // Nội dung chính
            out.println("<div class='container mt-5'>");
            out.println("<h1 class='text-center mb-4'>Quản lý sản phẩm</h1>");
            out.println("<table class='table table-bordered'>");
            out.println("<thead class='table-dark'><tr><th>Mã SP</th><th>Tên sản phẩm</th><th>Mô tả</th><th>Giá</th><th>Số lượng</th><th>Hình ảnh</th><th>Hành động</th></tr></thead>");
            out.println("<tbody>");

            // Truy vấn dữ liệu sản phẩm từ CSDL
            String query = "SELECT * FROM dtlsanpham";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String maSP = resultSet.getString("DtlMaSP");
                String tenSP = resultSet.getString("DtlTenSP");
                String moTa = resultSet.getString("DtlMoTa");
                double gia = resultSet.getDouble("DtlGia");
                int soLuong = resultSet.getInt("DtlSoLuong");
                String hinhAnh = resultSet.getString("DtlHinhAnh");

                out.println("<tr>");
                out.println("<td>" + maSP + "</td>");
                out.println("<td>" + tenSP + "</td>");
                out.println("<td>" + moTa + "</td>");
                out.println("<td>" + String.format("%,.2f VNĐ", gia) + "</td>");
                out.println("<td>" + soLuong + "</td>");
                out.println("<td><img src='" + hinhAnh + "' width='100'></td>");
                out.println("<td>");
                out.println("<a href='DtlEditProductServlet?DtlMaSP=" + maSP + "' class='btn btn-warning btn-sm'>Sửa</a> ");
                out.println("<a href='DtlDeleteProductServlet?DtlMaSP=" + maSP + "' class='btn btn-danger btn-sm'>Xóa</a>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("<a href='DtlAddProduct.html' class='btn btn-success'>Thêm sản phẩm mới</a>");
            out.println("</div>");
            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
