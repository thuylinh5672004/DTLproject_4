package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class DtlLogoutSanPhamServlet
 */
@WebServlet("/DtlLogoutSanPhamServlet")
public class DtlLogoutSanPhamServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Lấy session hiện tại
	        HttpSession session = request.getSession(false);

	        // Nếu có session, xóa nó đi
	        if (session != null) {
	            session.invalidate();
	        }

	        // Chuyển hướng về trang đăng nhập
	        response.sendRedirect("DtlLoginSanPham.html");
	    }
	}
