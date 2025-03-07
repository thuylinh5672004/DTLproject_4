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

import com.example.model.DtlDBConnect;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("/DtlDashboardServlet")
public class DtlDashboardServlet extends HttpServlet {
	  private static final long serialVersionUID = 1L;

	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");

	        String paramMaDH = request.getParameter("MaDH");
	        int maDH = 0; // Giá trị mặc định để tránh lỗi

	        if (paramMaDH != null && !paramMaDH.isEmpty()) {
	            try {
	                maDH = Integer.parseInt(paramMaDH);
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	                response.getWriter().println("Mã đơn hàng không hợp lệ.");
	                return;
	            }
	        } else {
	            response.getWriter().println("Mã đơn hàng không được để trống.");
	            return;
	        }

	        try (PrintWriter out = response.getWriter()) {
	            out.println("<!DOCTYPE html>");
	            out.println("<html><head><meta charset='UTF-8'><title>Cập nhật đơn hàng</title></head>");
	            out.println("<body>");
	            out.println("<h2>Cập nhật trạng thái đơn hàng</h2>");
	            out.println("<form action='DtlDashboardServlet' method='post'>");
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
	        response.setContentType("text/html;charset=UTF-8");

	        String paramMaDH = request.getParameter("MaDH");
	        String trangThai = request.getParameter("TrangThai");

	        int maDH = 0; // Giá trị mặc định để tránh lỗi

	        if (paramMaDH != null && !paramMaDH.isEmpty()) {
	            try {
	                maDH = Integer.parseInt(paramMaDH);
	            } catch (NumberFormatException e) {
	                e.printStackTrace();
	                response.getWriter().println("Mã đơn hàng không hợp lệ.");
	                return;
	            }
	        } else {
	            response.getWriter().println("Mã đơn hàng không được để trống.");
	            return;
	        }

	        if (trangThai == null || trangThai.isEmpty()) {
	            response.getWriter().println("Trạng thái đơn hàng không hợp lệ.");
	            return;
	        }

	        try (Connection connection = DtlDBConnect.getConnection()) {
	            String query = "UPDATE dtldonhang SET DtlTrangThai=? WHERE DtlMaDH=?";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setString(1, trangThai);
	            ps.setInt(2, maDH);

	            int rowsUpdated = ps.executeUpdate();
	            if (rowsUpdated > 0) {
	                response.sendRedirect("DtlProductServlet"); // Chuyển hướng về trang sản phẩm
	            } else {
	                response.getWriter().println("Cập nhật thất bại.");
	            }
	        } catch (Exception e) {
	            e.printStackTrace(response.getWriter());
	        }
	    }
	}