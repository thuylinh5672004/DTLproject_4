package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import com.example.model.DtlDBConnect;
/**
 * Servlet implementation class DtlSanPhamServlet
 */
@WebServlet("/DtlSanPhamServlet")
public class DtlSanPhamServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

	        // Lấy số lượng giỏ hàng từ session
	        String soLuongGio = (String) request.getSession().getAttribute("soLuongGio");
	        if (soLuongGio == null) soLuongGio = "0";

	        response.setContentType("text/html;charset=UTF-8");

	        try (Connection connection = DtlDBConnect.getConnection();
	             PrintWriter out = response.getWriter()) {

	            out.println("<!DOCTYPE html>");
	            out.println("<html lang='vi'>");
	            out.println("<head>");
	            out.println("<meta charset='UTF-8'>");
	            out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
	            out.println("<title>Quản Lý Sản Phẩm</title>");
	            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
	            out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css'>");
	            out.println("<style>");
	            out.println("body { background: #f8f9fa; font-family: 'Poppins', sans-serif; }");
	            out.println(".navbar { box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.1); }");
	            out.println(".card { border-radius: 10px; transition: 0.3s; }");
	            out.println(".card:hover { transform: scale(1.05); box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); }");
	            out.println(".card img { height: 200px; object-fit: cover; border-radius: 10px 10px 0 0; }");
	            out.println("</style>");
	            out.println("</head>");
	            out.println("<body>");
	            

	            // **Navbar**
	            out.println("<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>");
	            out.println("<div class='container-fluid'>");
	            out.println("<a class='navbar-brand' href='#'><img src='images/logo.png' width='40' height='40'> <span style='color: #FFFF00; font-weight: bold;'>SIÊU THỊ ĐIỆN MÁY</span></a>");
	            out.println("<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav'>");
	            out.println("<span class='navbar-toggler-icon'></span></button>");
	            out.println("<div class='collapse navbar-collapse' id='navbarNav'>");
	            out.println("<ul class='navbar-nav ms-auto'>");
	            out.println("<li class='nav-item'><a class='nav-link active' href='#'>Trang chủ</a></li>");
	            out.println("<li class='nav-item'><a class='nav-link' href='DtlTrangGioHangServlet'>Giỏ hàng <span class='badge bg-warning'>" + soLuongGio + "</span></a></li>");
	            out.println("<li class='nav-item'><a class='nav-link' href='DtlSanPhamServlet'>Sản phẩm</a></li>");
	            out.println("<li class='nav-item'><a class='nav-link' href='DtlLoginSanPham.html'>ĐĂNG XUẤT</a></li>");
	            out.println("<li class='nav-item'><a class='nav-link' href='DtlLoginSanPham.html'>ĐĂNG NHẬP</a></li>");
	            out.println("<li class='nav-item'><a class='nav-link' href='Dtlregister.html'>ĐĂNG KÝ</a></li>");
	            out.println("</ul>");
	            // Form tìm kiếm
	            out.println("<form class='d-flex' method='get' action='DtlSanPhamServlet'>");
	            out.println("<input class='form-control me-2' type='search' placeholder='Tìm sản phẩm...' name='search' value='" + searchQuery + "'>");
	            out.println("<button class='btn btn-outline-light' type='submit'>Tìm</button>");
	            out.println("</form>");
	            out.println("</div></div></nav>");
	            out.println("<div id='carouselExample' class='carousel slide' data-bs-ride='carousel'>");
	            out.println("<div class='carousel-inner'>");
	            out.println("<div class='carousel-item active'><img src='images/banner1.jpg' class='d-block w-100' alt='Quảng cáo 1'></div>");
	            out.println("<div class='carousel-item'><img src='images/banner2.jpg' class='d-block w-100' alt='Quảng cáo 2'></div>");
	            out.println("<div class='carousel-item'><img src='images/banner3.jpg' class='d-block w-100' alt='Quảng cáo 3'></div>");
	            out.println("</div>");
	            out.println("<button class='carousel-control-prev' type='button' data-bs-target='#carouselExample' data-bs-slide='prev'>");
	            out.println("<span class='carousel-control-prev-icon' aria-hidden='true'></span>");
	            out.println("</button>");
	            out.println("<button class='carousel-control-next' type='button' data-bs-target='#carouselExample' data-bs-slide='next'>");
	            out.println("<span class='carousel-control-next-icon' aria-hidden='true'></span>");
	            out.println("</button>");
	            out.println("</div>");

	            // **Danh sách sản phẩm**
	            out.println("<div class='container py-5'>");
	            out.println("<h2 class='text-center text-primary mb-4'>Danh sách sản phẩm</h2>");
	            out.println("<div class='row'>");

	            String query = "SELECT * FROM dtlsanpham WHERE dtlTenSP LIKE ? OR dtlMoTa LIKE ?";
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setString(1, "%" + searchQuery + "%");
	            statement.setString(2, "%" + searchQuery + "%");
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                String dtlmaSP = resultSet.getString("dtlMaSP");
	                String dtltenSP = resultSet.getString("dtlTenSP");
	                String dtlmoTa = resultSet.getString("dtlMoTa");
	                double dtlgia = resultSet.getDouble("dtlGia");
	                int dtlsoLuong = resultSet.getInt("dtlSoLuong");
	                String dtlhinhAnh = resultSet.getString("dtlHinhAnh");

	                out.println("<div class='col-md-4'>");
	                out.println("<div class='card mb-4 shadow-sm'>");
	                out.println("<img src='" + dtlhinhAnh + "' class='card-img-top' alt='" + dtltenSP + "'>");
	                out.println("<div class='card-body'>");
	                out.println("<h5 class='card-title'>" + dtltenSP + "</h5>");
	                out.println("<p class='card-text'>" + dtlmoTa + "</p>");
	                out.println("<p class='card-text'><strong>Giá:</strong> " + dtlgia + " VNĐ</p>");
	                out.println("<p class='card-text'><strong>Số lượng:</strong> " + dtlsoLuong + "</p>");
	                out.println("<a href='DtlThanhToanServlet?maSP=" + dtlmaSP + "' class='btn btn-success'><i class='bi bi-cart'></i> MUA</a>");
	                out.println("<a href='DtlDeleteProductServlet?maSP=" + dtlmaSP + "' class='btn btn-danger'><i class='bi bi-trash'></i> Xóa</a>");
	                out.println("<a href='DtlGioHangServlet?maSP=" + dtlmaSP + "&action=add' class='btn btn-primary'><i class='bi bi-plus'></i> Thêm vào giỏ</a>");
	                out.println("</div></div></div>");
	            }

	            out.println("</div></div>");

	            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
	            out.println("</body>");
	            out.println("</html>");
	        } catch (Exception e) {
	            e.printStackTrace(response.getWriter());
	        }
	    }
	}