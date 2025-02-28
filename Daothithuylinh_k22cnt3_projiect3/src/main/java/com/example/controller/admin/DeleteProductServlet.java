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
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maSP = request.getParameter("MaSP");

        try (Connection connection = DBConnect.getConnection()) {
            String query = "DELETE FROM sanpham WHERE MaSP=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, maSP);
            int rowsDeleted = ps.executeUpdate();
            if (rowsDeleted > 0) {
                response.sendRedirect("ListProductsServlet");
            } else {
                response.getWriter().println("Xóa thất bại.");
            }
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}