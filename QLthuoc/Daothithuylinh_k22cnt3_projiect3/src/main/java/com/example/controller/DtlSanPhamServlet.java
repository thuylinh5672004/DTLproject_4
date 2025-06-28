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

import com.example.model.DtlDBConnect;
/**
 * Servlet implementation class SanPhamServlet
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

	        // Lấy số lượng giỏ hàng từ session hoặc mặc định là 0
	        String soLuongGio = (String) request.getSession().getAttribute("soLuongGio");
	        if (soLuongGio == null) {
	            soLuongGio = "0"; // Nếu chưa có, đặt mặc định là 0
	        }

	        response.setContentType("text/html;charset=UTF-8");

	        try (Connection connection = DtlDBConnect.getConnection();
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
	            out.println("<li class='nav-item'><a class='nav-link' href='DtlTrangGioHangServlet'>Giỏ hàng <span class='badge bg-warning'>" + soLuongGio + "</span></a></li>");
	            out.println("<li class='nav-item'><a class='nav-link' href='DtlSanPhamServlet'>Sản Phẩm</a></li>");
	            out.println("<li class='nav-item'><a class='nav-link' href='#'>Giới Thiệu</a></li>");
	            out.println("<li class='nav-item'><a class='nav-link' href='#'>Liên Hệ</a></li>");
	            out.println("</ul>");

	            // Form tìm kiếm
	            out.println("<form class='d-flex' method='get' action='DtlSanPhamServlet'>");
	            out.println("<input class='form-control me-2' type='search' placeholder='Tìm sản phẩm...' name='search' value='" + searchQuery + "'>");
	            out.println("<button class='btn btn-outline-light' type='submit'>Tìm kiếm</button>");
	            out.println("</form>");

	            out.println("</div>"); // Đóng div navbar-collapse
	            out.println("</div>"); // Đóng div container-fluid
	            out.println("</nav>");

	            // **Hiển thị danh sách sản phẩm**
	            out.println("<div class='container py-5'>");
	            out.println("<div class='row'>");

	            String query = "SELECT * FROM dtlsanpham WHERE DtlTenSP LIKE ? OR DtlMoTa LIKE ?";
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setString(1, "%" + searchQuery + "%");
	            statement.setString(2, "%" + searchQuery + "%");
	            ResultSet resultSet = statement.executeQuery();

	            while (resultSet.next()) {
	                String DtlmaSP = resultSet.getString("DtlMaSP");
	                String DtltenSP = resultSet.getString("DtlTenSP");
	                String DtlmoTa = resultSet.getString("DtlMoTa");
	                double Dtlgia = resultSet.getDouble("DtlGia");
	                int DtlsoLuong = resultSet.getInt("DtlSoLuong");
	                String DtlhinhAnh = resultSet.getString("DtlHinhAnh");

	                out.println("<div class='col-md-4'>");
	                out.println("<div class='card mb-4 shadow-sm'>");
	                out.println("<img src='" + DtlhinhAnh + "' class='card-img-top' alt='" + DtltenSP + "'>");
	                out.println("<div class='card-body'>");
	                out.println("<h5 class='card-title'>" + DtltenSP + "</h5>");
	                out.println("<p class='card-text'>" + DtlmoTa + "</p>");
	                out.println("<p class='card-text'><strong>Giá:</strong> " + Dtlgia + " VND</p>");
	                out.println("<p class='card-text'><strong>Số lượng:</strong> " + DtlsoLuong + "</p>");
	                out.println("<a href='DtlThanhToanServlet?maSP=" + DtlmaSP + "' class='btn btn-success'>MUA</a> ");
	                out.println("<a href='DtlDeleteProductServlet?maSP=" + DtlmaSP + "' class='btn btn-danger'>Xóa</a>");
	                out.println("<a href='DtlGioHangServlet?maSP=" + DtlmaSP + "&action=add' class='btn btn-success'>Thêm vào giỏ hàng</a>");
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