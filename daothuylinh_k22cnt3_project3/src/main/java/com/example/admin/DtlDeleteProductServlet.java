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
 * Servlet implementation class DtlDeleteProductServlet
 */
@WebServlet("/DtlDeleteProductServlet")
public class DtlDeleteProductServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String maSP = request.getParameter("DtlMaSP");
	        try (Connection connection = DtlDBConnect.getConnection()) {
	            String query = "DELETE FROM dtlsanpham WHERE DtlMaSP = ?";
	            PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setString(1, maSP);
	            preparedStatement.executeUpdate();
	            response.sendRedirect("DtlProductServlet");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
