package com.example.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.sql.*;
import com.example.model.DBConnect;



/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");

	        try (Connection connection = DBConnect.getConnection();
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
	            out.println("<div class='container mt-5'>");
	            out.println("<h1 class='text-center mb-4'>Quản lý sản phẩm</h1>");
	            out.println("<table class='table table-bordered'>");
	            out.println("<thead class='table-dark'><tr><th>Mã SP</th><th>Tên sản phẩm</th><th>Mô tả</th><th>Giá</th><th>Số lượng</th><th>Hình ảnh</th><th>Hành động</th></tr></thead>");
	            out.println("<tbody>");

	            // Truy vấn dữ liệu sản phẩm từ CSDL
	            String query = "SELECT * FROM sanpham";
	            Statement statement = connection.createStatement();
	            ResultSet resultSet = statement.executeQuery(query);

	            while (resultSet.next()) {
	                String maSP = resultSet.getString("MaSP");
	                String tenSP = resultSet.getString("TenSP");
	                String moTa = resultSet.getString("MoTa");
	                double gia = resultSet.getDouble("Gia");
	                int soLuong = resultSet.getInt("SoLuong");
	                String hinhAnh = resultSet.getString("HinhAnh");

	                out.println("<tr>");
	                out.println("<td>" + maSP + "</td>");
	                out.println("<td>" + tenSP + "</td>");
	                out.println("<td>" + moTa + "</td>");
	                out.println("<td>" + String.format("%,.2f VNĐ", gia) + "</td>");
	                out.println("<td>" + soLuong + "</td>");
	                out.println("<td><img src='" + hinhAnh + "' width='100' height='auto'></td>");
	                out.println("<td>");
	                out.println("<a href='EditProductServlet?MaSP=" + maSP + "' class='btn btn-warning btn-sm'>Sửa</a> ");
	                out.println("<a href='DeleteProductServlet?MaSP=" + maSP + "' class='btn btn-danger btn-sm'>Xóa</a>");
	                out.println("</td>");
	                out.println("</tr>");
	            }

	            out.println("</tbody>");
	            out.println("</table>");
	            out.println("<a href='add_product.html' class='btn btn-success'>Thêm sản phẩm mới</a>");
	            out.println("</div>");
	            out.println("</body></html>");

	        } catch (Exception e) {
	            e.printStackTrace(response.getWriter());
	        }
	    }
	}