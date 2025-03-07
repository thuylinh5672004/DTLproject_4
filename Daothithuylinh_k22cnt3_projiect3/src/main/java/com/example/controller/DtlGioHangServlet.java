package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.model.DtlSanPham;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * Servlet implementation class GioHangServlet
 */
@WebServlet("/DtlGioHangServlet")
public class DtlGioHangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String maSP = request.getParameter("dtlmaSP");
        String action = request.getParameter("action");

        // Lấy giỏ hàng từ session hoặc tạo mới nếu chưa có
        Map<String, Integer> gioHang = (Map<String, Integer>) session.getAttribute("gioHang");
        if (gioHang == null) {
            gioHang = new HashMap<>();
        }

        if ("add".equals(action) && maSP != null) {
            int soLuong = gioHang.getOrDefault(maSP, 0);
            gioHang.put(maSP, soLuong + 1);
            session.setAttribute("gioHang", gioHang);
        }

        // Quay lại trang trước đó
        String referer = request.getHeader("Referer");
        if (referer != null) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect("DtlTrangGioHangServlet"); // Nếu không có referer, quay về trang sản phẩm
        }
    }
}