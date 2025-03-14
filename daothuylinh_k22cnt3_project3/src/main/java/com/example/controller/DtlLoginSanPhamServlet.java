package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.example.model.DtlTaikhoan;
import com.example.model.DtlTaikhoanDAO;

/**
 * Servlet implementation class DtlLoginSanPhamServlet
 */
@WebServlet("/DtlLoginSanPhamServlet")
public class DtlLoginSanPhamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String jsonResponse;

        // Kiểm tra tài khoản cứng
        if ("Thuylinh".equals(username) && "linh@11".equals(password)) {
            // Tạo session cho user
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", "admin");

            jsonResponse = "{\"success\": true, \"role\": \"admin\"}";
        } else {
            jsonResponse = "{\"success\": false, \"message\": \"Sai tài khoản hoặc mật khẩu!\"}";
        }

        out.print(jsonResponse);
        out.flush();
    }
}