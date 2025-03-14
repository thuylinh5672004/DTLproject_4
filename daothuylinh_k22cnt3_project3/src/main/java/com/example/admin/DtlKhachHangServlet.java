package com.example.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

import com.example.model.DtlDBConnect;
import com.example.model.DtlKhachHang;
import com.example.model.DtlKhachHangDAO;
/**
 * Servlet implementation class DtlKhachHangServlet
 */
@WebServlet("/DtlKhachHangServlet")
public class DtlKhachHangServlet extends HttpServlet {
	  private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();

	        out.println("<html><head><title>Danh sách khách hàng</title>");
	        out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
	        out.println("</head><body>");

	        out.println("<div class='container mt-5'>");
	        out.println("<h1 class='text-center mb-4 text-primary'>Danh sách khách hàng</h1>");
	        out.println("<table class='table table-hover table-bordered'>");
	        out.println("<thead class='table-dark'><tr><th>Mã KH</th><th>Họ Tên</th><th>Email</th><th>Số Điện Thoại</th><th>Địa Chỉ</th><th>Ngày Tạo</th><th>Hành động</th></tr></thead>");
	        out.println("<tbody>");

	        try (Connection connection = DtlDBConnect.getConnection();
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery("SELECT * FROM dtlkhachhang")) {

	            while (resultSet.next()) {
	                String maKH = resultSet.getString("DtlMaKH");
	                String hoTen = resultSet.getString("DtlHoTen");
	                String email = resultSet.getString("DtlEmail");
	                String soDienThoai = resultSet.getString("DtlSoDienThoai");
	                String diaChi = resultSet.getString("DtlDiaChi");
	                String ngayTao = resultSet.getString("DtlNgayTao");

	                out.println("<tr>");
	                out.println("<td>" + maKH + "</td>");
	                out.println("<td>" + hoTen + "</td>");
	                out.println("<td>" + email + "</td>");
	                out.println("<td>" + soDienThoai + "</td>");
	                out.println("<td>" + diaChi + "</td>");
	                out.println("<td>" + ngayTao + "</td>");
	                out.println("<td>");
	                out.println("<a href='DtlKhachHangServlet?DtlMaKH=" + maKH + "' class='btn btn-info btn-sm'>Chi tiết</a> ");
	                out.println("<a href='Dtleditkhachhang.html?DtlMaKH=" + maKH + "' class='btn btn-warning btn-sm'>Sửa</a> ");
	                out.println("<a href='Dtldeletekhachhang.html?DtlMaKH=" + maKH + "' class='btn btn-danger btn-sm'>Xóa</a>");
	                out.println("</td>");
	                out.println("</tr>");
	            }
	        } catch (Exception e) {
	            out.println("<tr><td colspan='7' class='text-danger text-center'>Lỗi khi tải dữ liệu: " + e.getMessage() + "</td></tr>");
	            e.printStackTrace();
	        }

	        out.println("</tbody>");
	        out.println("</table>");
	        out.println("<a href='Dtladdkhachhang.html' class='btn btn-success'>Thêm khách hàng</a>");
	        out.println("</div>");
	        out.println("</body></html>");
	    }
	}