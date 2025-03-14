package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;
import java.util.Map;

import com.example.model.DtlDBConnect;
/**
 * Servlet implementation class DtlXuLyThanhToanServlet
 */
@WebServlet("/DtlXuLyThanhToanServlet")
public class DtlXuLyThanhToanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Map<String, Integer> gioHang = (Map<String, Integer>) session.getAttribute("gioHang");

        if (gioHang == null || gioHang.isEmpty()) {
            response.sendRedirect("DtlSanPhamServlet");
            return;
        }

        String hoten = request.getParameter("hoten");
        String email = request.getParameter("email");
        String sodienthoai = request.getParameter("sodienthoai");
        String diachi = request.getParameter("diachi");
        String phuongthuc = request.getParameter("phuongthuc");

        try (Connection conn = DtlDBConnect.getConnection()) {
            String sql = "INSERT INTO dtldonhang (hoten, email, sodienthoai, diachi, phuongthuc) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hoten);
            ps.setString(2, email);
            ps.setString(3, sodienthoai);
            ps.setString(4, diachi);
            ps.setString(5, phuongthuc);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.removeAttribute("gioHang");
        response.sendRedirect("DtlThanhToanThanhCong.html");
    }
}