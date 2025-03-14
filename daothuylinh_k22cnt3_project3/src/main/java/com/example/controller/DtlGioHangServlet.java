package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class DtlGioHangServlet
 */
@WebServlet("/DtlGioHangServlet")
public class DtlGioHangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String maSP = request.getParameter("maSP");

        if (maSP != null) {
            // Lấy giỏ hàng từ session hoặc tạo mới nếu chưa có
            Map<String, Integer> gioHang = (Map<String, Integer>) session.getAttribute("gioHang");
            if (gioHang == null) {
                gioHang = new HashMap<>();
            }

            // Cập nhật số lượng sản phẩm trong giỏ hàng
            gioHang.put(maSP, gioHang.getOrDefault(maSP, 0) + 1);

            // Lưu lại giỏ hàng vào session
            session.setAttribute("gioHang", gioHang);
            session.setAttribute("soLuongGio", String.valueOf(gioHang.size()));
        }

        // Quay lại trang sản phẩm
        response.sendRedirect("DtlSanPhamServlet");
    }
}