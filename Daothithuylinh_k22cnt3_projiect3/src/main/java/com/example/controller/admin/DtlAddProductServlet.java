package com.example.controller.admin;

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
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/DtlAddProductServlet")
public class DtlAddProductServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String maSP = request.getParameter("dtlMaSP");
	        String tenSP = request.getParameter("dtlTenSP");
	        String moTa = request.getParameter("dtlMoTa");
	        double gia = Double.parseDouble(request.getParameter("dtlGia"));
	        int soLuong = Integer.parseInt(request.getParameter("dtlSoLuong"));
	        String hinhAnh = request.getParameter("dtlHinhAnh");

	        try (Connection connection = DtlDBConnect.getConnection()) {
	            String query = "INSERT INTO dtlsanpham (dtlMaSP, dtlTenSP, dtlMoTa, dtlGia, dtlSoLuong, dtlHinhAnh) VALUES (?, ?, ?, ?, ?, ?)";
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