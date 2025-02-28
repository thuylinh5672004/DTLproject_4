package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.model.SanPham;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class GioHangServlet
 */
@WebServlet("/GioHangServlet")
public class GioHangServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();

	        // Lấy giỏ hàng từ session (nếu chưa có thì tạo mới)
	        List<SanPham> gioHang = (List<SanPham>) session.getAttribute("gioHang");
	        if (gioHang == null) {
	            gioHang = new ArrayList<>();
	        }

	        // Lấy thông tin sản phẩm từ form
	        String maSP = request.getParameter("maSP");
	        String tenSP = request.getParameter("tenSP");
	        double gia = Double.parseDouble(request.getParameter("gia"));

	        // Thêm sản phẩm vào giỏ hàng
	        gioHang.add(new SanPham(0, maSP, tenSP, gia, 0, 0, 0, tenSP));

	        // Lưu giỏ hàng vào session
	        session.setAttribute("gioHang", gioHang);

	        // Chuyển hướng đến trang giỏ hàng
	        response.sendRedirect("GioHang.html");
	    }
	}