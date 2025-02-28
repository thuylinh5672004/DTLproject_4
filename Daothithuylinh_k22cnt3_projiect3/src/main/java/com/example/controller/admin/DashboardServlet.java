package com.example.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.example.model.DBConnect;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/DashboardServlet")
public class DashboardServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int maDH = Integer.parseInt(request.getParameter("MaDH"));
	        response.setContentType("text/html;charset=UTF-8");

	        try (PrintWriter out = response.getWriter()) {
	            out.println("<!DOCTYPE html>");
	            out.println("<html><head><meta charset='UTF-8'><title>Cập nhật đơn hàng</title></head>");
	            out.println("<body>");
	            out.println("<h2>Cập nhật trạng thái đơn hàng</h2>");
	            out.println("<form action='UpdateOrderServlet' method='post'>");
	            out.println("<input type='hidden' name='MaDH' value='" + maDH + "'>");
	            out.println("Trạng thái: <select name='TrangThai'>");
	            out.println("<option value='Chờ xử lý'>Chờ xử lý</option>");
	            out.println("<option value='Đang giao hàng'>Đang giao hàng</option>");
	            out.println("<option value='Hoàn thành'>Hoàn thành</option>");
	            out.println("</select><br>");
	            out.println("<button type='submit'>Cập nhật</button>");
	            out.println("</form>");
	            out.println("</body></html>");
	        }
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        int maDH = Integer.parseInt(request.getParameter("MaDH"));
	        String trangThai = request.getParameter("TrangThai");

	        try (Connection connection = DBConnect.getConnection()) {
	            String query = "UPDATE donhang SET TrangThai=? WHERE MaDH=?";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setString(1, trangThai);
	            ps.setInt(2, maDH);

	            int rowsUpdated = ps.executeUpdate();
	            if (rowsUpdated > 0) {
	                response.sendRedirect("ProductServlet");
	            } else {
	                response.getWriter().println("Cập nhật thất bại.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace(response.getWriter());
	        }
	    }
	}