package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.example.model.DBThuocDao;

import java.sql.*;


/**
 * Servlet implementation class ThuocServlet
 */
@WebServlet("/ThuocServlet")
public class ThuocServlet extends HttpServlet {
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

	   	        response.setContentType("text/html;charset=UTF-8");

	   	        try (Connection connection = DBThuocDao.getConnection();
	   	             PrintWriter out = response.getWriter()) {
	            // Bắt đầu HTML trả về
	            out.println("<!DOCTYPE html>");
	            out.println("<html lang='vi'>");
	            out.println("<head>");
	            out.println("<meta charset='UTF-8'>");
	            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
	            out.println("<title>Quản Lý Thuốc</title>");
	            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
	            out.println("<style>");
	            out.println("body { background-color: #f8f9fa; font-family: Arial, sans-serif; }");
	            out.println(".thuoc-card { margin-bottom: 20px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); border-radius: 8px; overflow: hidden; background-color: #fff; transition: transform 0.3s ease; }");
	            out.println(".thuoc-card:hover { transform: translateY(-5px); }");
	            out.println(".thuoc-card img { width: 100%; height: 250px; object-fit: cover; }");
	            out.println(".thuoc-card-body { padding: 20px; }");
	            out.println(".thuoc-name { font-size: 1.25rem; font-weight: bold; color: #333; }");
	            out.println(".thuoc-info { font-size: 1rem; color: #555; margin-top: 5px; }");
	            out.println(".btn-group { margin-top: 10px; }");
	            out.println(".footer { background-color: #343a40; color: white; padding: 10px 20px; text-align: center; margin-top: 20px; }");
	            out.println("</style>");
	            out.println("</head>");
	            out.println("<body>");

	            // Thanh điều hướng
	            out.println("<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>");
	            out.println("<div class='container-fluid'>");
	            out.println("<a class='navbar-brand' href='ListThuoc'>QUẢN LÝ THUỐC</a>");
	           
	           
	            out.println("</button>");
	            out.println("<div class='collapse navbar-collapse' id='navbarNav'>");
	            out.println("<ul class='navbar-nav ms-auto'>");
	            out.println("<li class='nav-item'><a class='nav-link' href='BookStoreServlet'>Thuylinh</a></li>");
	            out.println("<li class='nav-item'><a class='nav-link' href=''>Sản Phẩm</a></li>"); // Đã thêm mục "Sản Phẩm"
	            out.println("<li class='nav-item'><a class='nav-link' href='#'>Giới Thiệu</a></li>");
	            out.println("<li class='nav-item'><a class='nav-link' href='#'>Liên Hệ</a></li>");
	            out.println("<li class='nav-item'>");
	          
	            out.println("<form class='d-flex ms-auto' method='get' action='ListThuoc'>");
	            out.println("<input class='form-control me-2' type='search' placeholder='Tìm thuốc...' aria-label='Search' name='search' value='" + searchQuery + "'>");
	            out.println("<button class='btn btn-outline-light' type='submit'>Tìm kiếm</button>");
	            out.println("</form>");
	            out.println("</div>");
	            out.println("</nav>");

	            // Hiển thị danh sách thuốc
	            out.println("<div class='container py-5'>");
	            out.println("<div class='row'>");

	            // Truy vấn cơ sở dữ liệu để tìm kiếm thuốc
	            String query = "SELECT * FROM thuoc_daothithuylinh WHERE TenThuoc LIKE ? OR LoaiThuoc LIKE ?";
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setString(1, "%" + searchQuery + "%");
	            statement.setString(2, "%" + searchQuery + "%");
	            ResultSet resultSet = statement.executeQuery();

	            // Hiển thị thuốc dưới dạng card
	            while (resultSet.next()) {
	                int id = resultSet.getInt("ID");
	                String tenThuoc = resultSet.getString("TenThuoc");
	                String ngayNhap = resultSet.getString("NgayNhap");
	                String loaiThuoc = resultSet.getString("LoaiThuoc");
	                int soLuong = resultSet.getInt("SoLuong");
	                double donGia = resultSet.getDouble("DonGia");
	                String anhThuoc = resultSet.getString("AnhThuoc");

	                out.println("<div class='col-md-4'>");
	                out.println("<div class='card thuoc-card'>");
	                out.println("<img src='" + anhThuoc + "' class='card-img-top' alt='" + tenThuoc + "'>");
	                out.println("<div class='thuoc-card-body'>");
	                out.println("<h5 class='thuoc-name'>" + tenThuoc + "</h5>");
	                out.println("<p class='thuoc-info'>Ngày nhập: " + ngayNhap + "</p>");
	                out.println("<p class='thuoc-info'>Loại: " + loaiThuoc + "</p>");
	                out.println("<p class='thuoc-info'>Số lượng: " + soLuong + "</p>");
	                out.println("<p class='thuoc-info'>Đơn giá: " + donGia + " VND</p>");
	                out.println("<div class='btn-group'>");
	                out.println("<div class='btn-group' role='group' aria-label='Basic example'>");
	                out.println("<a href='EditThuocServlet?id=" + id + "' class='btn btn-primary me-3 rounded'>Sửa</a>");
	                out.println("<a href='DeleteThuocServlet?id=" + id + "' class='btn btn-danger me-3 rounded'>Xóa</a>");
	                out.println("<a href='AddThuocServlet?id=" + id + "' class='btn btn-success rounded'>Thêm Mới</a>");
	                out.println("</div>");
	                out.println("</div>");
	                out.println("</div>");
	                out.println("</div>");
	                out.println("</div>");
	            }

	            out.println("</div>");
	            out.println("</div>");

	            // Footer
	            out.println("<div class='footer'>");
	            out.println("<p>&copy; 2024 Quản Lý Thuốc</p>");
	            out.println("</div>");

	            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js'></script>");
	            out.println("</body>");
	            out.println("</html>");
	        } catch (Exception e) {
	            e.printStackTrace(response.getWriter());
	        }
	    }
	}