package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.model.DBConnect;
/**
 * Servlet implementation class SanPhamServlet
 */
@WebServlet("/SanPhamServlet")
public class SanPhamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, String soLuongGio) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    break;
                }
            }
        }

        // Lấy từ khóa tìm kiếm nếu có
        String searchQuery = request.getParameter("search");
        if (searchQuery == null) searchQuery = "";

        response.setContentType("text/html;charset=UTF-8");

        try (Connection connection = DBConnect.getConnection();
             PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html lang='vi'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
            out.println("<title>Quản Lý Sản Phẩm</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");

            // **Navbar Bootstrap**
            out.println("<style>.navbar-brand { color: yellow !important; font-weight: bold; }</style>");
            out.println("<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>");
            out.println("<div class='container-fluid'>");
            out.println("<img src='images/logo.png' alt='HC' width='40' height='40' class='d-inline-block align-text-top'>");
            out.println("&nbsp;&nbsp;&nbsp;");
            out.println(" <span style='color: yellow; font-weight: bold;'>SIÊU THỊ ĐIỆN MÁY</span></a>");

            
            out.println("<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav' aria-controls='navbarNav' aria-expanded='false' aria-label='Toggle navigation'>");
            out.println("<span class='navbar-toggler-icon'></span>");
            out.println("</button>");

            out.println("<div class='collapse navbar-collapse' id='navbarNav'>");
            out.println("<ul class='navbar-nav ms-auto'>");
            out.println("<li class='nav-item'><a class='nav-link' href='#'>Trang chủ</a></li>");
            out.println("<li class='nav-item'><a class='nav-link' href='GioHangServlet'>Giỏ hàng <span class='badge bg-warning'>" + soLuongGio + "</span></a></li>");
            out.println("<li class='nav-item'><a class='nav-link' href='SanPhamServlet'>Sản Phẩm</a></li>");
            out.println("<li class='nav-item'><a class='nav-link' href='#'>Giới Thiệu</a></li>");
            out.println("<li class='nav-item'><a class='nav-link' href='#'>Liên Hệ</a></li>");
            out.println("</ul>");

            // Form tìm kiếm
            out.println("<form class='d-flex' method='get' action='SanPhamServlet'>");
            out.println("<input class='form-control me-2' type='search' placeholder='Tìm sản phẩm...' name='search' value='" + searchQuery + "'>");
            out.println("<button class='btn btn-outline-light' type='submit'>Tìm kiếm</button>");
            out.println("</form>");

            out.println("</div>"); // Đóng div navbar-collapse
            out.println("</div>"); // Đóng div container-fluid
            out.println("</nav>");

            // **Hiển thị danh sách sản phẩm**
            out.println("<div class='container py-5'>");
            out.println("<div class='row'>");

            String query = "SELECT * FROM sanpham WHERE TenSP LIKE ? OR MoTa LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + searchQuery + "%");
            statement.setString(2, "%" + searchQuery + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String maSP = resultSet.getString("MaSP");
                String tenSP = resultSet.getString("TenSP");
                String moTa = resultSet.getString("MoTa");
                double gia = resultSet.getDouble("Gia");
                int soLuong = resultSet.getInt("SoLuong");
                String hinhAnh = resultSet.getString("HinhAnh");

                out.println("<div class='col-md-4'>");
                out.println("<div class='card mb-4 shadow-sm'>");
                out.println("<img src='" + hinhAnh + "' class='card-img-top' alt='" + tenSP + "'>");
                out.println("<div class='card-body'>");
                out.println("<h5 class='card-title'>" + tenSP + "</h5>");
                out.println("<p class='card-text'>" + moTa + "</p>");
                out.println("<p class='card-text'><strong>Giá:</strong> " + gia + " VND</p>");
                out.println("<p class='card-text'><strong>Số lượng:</strong> " + soLuong + "</p>");
                out.println("<a href='EditProductServlet?maSP=" + maSP + "' class='btn btn-primary'>Sửa</a> ");
                out.println("<a href='DeleteProductServlet?maSP=" + maSP + "' class='btn btn-danger'>Xóa</a>");
                out.println("</div></div></div>");
            }

            out.println("</div>");
            out.println("</div>");

            // **Script Bootstrap**
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}