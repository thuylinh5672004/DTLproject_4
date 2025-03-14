package com.example.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import com.example.model.DtlDBConnect;

/**
 * Servlet implementation class DtlAddProductServlet
 */
@WebServlet("/DtlAddProductServlet")
public class DtlAddProductServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Nhận dữ liệu từ form
	        String dtlMaSP = request.getParameter("DtlMaSP");
	        String dtlTenSP = request.getParameter("DtlTenSP");
	        String dtlMoTa = request.getParameter("DtlMoTa");
	        String dtlGia = request.getParameter("DtlGia");
	        String dtlSoLuong = request.getParameter("DtlSoLuong");
	        String dtlHinhAnh = request.getParameter("DtlHinhAnh");

	        // Kết nối CSDL và thực hiện INSERT
	        try (Connection connection = DtlDBConnect.getConnection()) {
	            String sql = "INSERT INTO dtlsanpham (DtlMaSP, DtlTenSP, DtlMoTa, DtlGia, DtlSoLuong, DtlHinhAnh) VALUES (?, ?, ?, ?, ?, ?)";
	            PreparedStatement statement = connection.prepareStatement(sql);
	            statement.setString(1, dtlMaSP);
	            statement.setString(2, dtlTenSP);
	            statement.setString(3, dtlMoTa);
	            statement.setDouble(4, Double.parseDouble(dtlGia));
	            statement.setInt(5, Integer.parseInt(dtlSoLuong));
	            statement.setString(6, dtlHinhAnh);

	            int rowsInserted = statement.executeUpdate();
	            if (rowsInserted > 0) {
	                response.sendRedirect("DtlProductServlet?success=1"); // Chuyển hướng về trang danh sách sản phẩm
	            } else {
	                response.sendRedirect("DtlAddProduct.html?error=1");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.sendRedirect("DtlAddProduct.html?error=1");
	        }
	    }
	}
