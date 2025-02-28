package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import com.example.model.DBThuocDao;

import java.sql.*;

/**
 * Servlet implementation class DeleteThuocServlet
 */
@WebServlet("/DeleteThuocServlet")
public class DeleteThuocServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Lấy mã thuốc từ yêu cầu
	        int maThuoc = Integer.parseInt(request.getParameter("maThuoc"));

	        try (Connection connection = DBThuocDao.getConnection()) {
	            // Câu lệnh SQL để xóa thuốc
	            String query = "DELETE FROM thuoc_daothithuylinh WHERE maThuoc = ?";
	            PreparedStatement statement = connection.prepareStatement(query);
	            statement.setInt(1, maThuoc);

	            // Thực thi câu lệnh và kiểm tra số hàng bị ảnh hưởng
	            int rowsAffected = statement.executeUpdate();
	            if (rowsAffected > 0) {
	                // Xóa thành công, chuyển hướng về danh sách thuốc
	                response.sendRedirect("ThuocServlet");
	            } else {
	                // Không tìm thấy thuốc để xóa
	                response.setContentType("text/html;charset=UTF-8");
	                response.getWriter().write("Error: Thuốc không tồn tại!");
	            }
	        } catch (Exception e) {
	            // Xử lý lỗi và hiển thị thông báo lỗi
	            response.setContentType("text/html;charset=UTF-8");
	            response.getWriter().write("<h3>Error: " + e.getMessage() + "</h3>");
	            e.printStackTrace(response.getWriter());
	        }
	    }
	}