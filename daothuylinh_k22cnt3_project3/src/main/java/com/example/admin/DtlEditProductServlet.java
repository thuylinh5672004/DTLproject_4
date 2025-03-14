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
 * Servlet implementation class DtlEditProductServlet
 */
@WebServlet("/DtlEditProductServlet")
public class DtlEditProductServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String maSP = request.getParameter("DtlMaSP");
	        try (Connection connection = DtlDBConnect.getConnection()) {
	            String query = "SELECT * FROM dtlsanpham WHERE DtlMaSP = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, maSP);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            
	            if (resultSet.next()) {
	                request.setAttribute("product", resultSet);
	                request.getRequestDispatcher("Dtledit_product.html").forward(request, response);
	            } else {
	                response.sendRedirect("DtlProductServlet");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String maSP = request.getParameter("DtlMaSP");
	        String tenSP = request.getParameter("DtlTenSP");
	        String moTa = request.getParameter("DtlMoTa");
	        double gia = Double.parseDouble(request.getParameter("DtlGia"));
	        int soLuong = Integer.parseInt(request.getParameter("DtlSoLuong"));
	        String hinhAnh = request.getParameter("DtlHinhAnh");

	        try (Connection connection = DtlDBConnect.getConnection()) {
	            String query = "UPDATE dtlsanpham SET DtlTenSP=?, DtlMoTa=?, DtlGia=?, DtlSoLuong=?, DtlHinhAnh=? WHERE DtlMaSP=?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, tenSP);
	            preparedStatement.setString(2, moTa);
	            preparedStatement.setDouble(3, gia);
	            preparedStatement.setInt(4, soLuong);
	            preparedStatement.setString(5, hinhAnh);
	            preparedStatement.setString(6, maSP);
	            
	            preparedStatement.executeUpdate();
	            response.sendRedirect("DtlProductServlet");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}