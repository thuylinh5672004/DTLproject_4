package com.example.controller.admin;

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
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String maSP = request.getParameter("MaSP");
	        String tenSP = request.getParameter("TenSP");
	        String moTa = request.getParameter("MoTa");
	        double gia = Double.parseDouble(request.getParameter("Gia"));
	        int soLuong = Integer.parseInt(request.getParameter("SoLuong"));
	        String hinhAnh = request.getParameter("HinhAnh");

	        try (Connection connection = DBConnect.getConnection()) {
	            String query = "INSERT INTO sanpham (MaSP, TenSP, MoTa, Gia, SoLuong, HinhAnh) VALUES (?, ?, ?, ?, ?, ?)";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setString(1, maSP);
	            ps.setString(2, tenSP);
	            ps.setString(3, moTa);
	            ps.setDouble(4, gia);
	            ps.setInt(5, soLuong);
	            ps.setString(6, hinhAnh);

	            int rowsInserted = ps.executeUpdate();
	            if (rowsInserted > 0) {
	                response.sendRedirect("ListProductsServlet");
	            } else {
	                response.getWriter().println("Lỗi thêm sản phẩm.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace(response.getWriter());
	        }
	    }
	}