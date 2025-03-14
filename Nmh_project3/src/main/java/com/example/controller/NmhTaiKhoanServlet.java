package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.example.model.NmhTaiKhoanDAO;

/**
 * Servlet implementation class NmhTaiKhoanServlet
 */
@WebServlet("/NmhTaiKhoanServlet")
public class NmhTaiKhoanServlet extends HttpServlet {
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

	        if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
	            jsonResponse = "{\"success\": false, \"message\": \"Vui lòng nhập tài khoản và mật khẩu!\"}";
	        } else if ("admin".equals(username) && "123456".equals(password)) {
	            HttpSession session = request.getSession();
	            session.setAttribute("username", username);
	            session.setAttribute("role", "admin");

	            jsonResponse = "{\"success\": true, \"role\": \"admin\"}";
	        } else {
	            jsonResponse = "{\"success\": false, \"message\": \"Sai tài khoản hoặc mật khẩu!\"}";
	        }

	        out.print(jsonResponse);
	        out.flush();
	        out.close();
	    }

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");

	        HttpSession session = request.getSession(false);
	        PrintWriter out = response.getWriter();

	        if (session != null && session.getAttribute("username") != null) {
	            String role = (String) session.getAttribute("role");
	            out.print("{\"success\": true, \"role\": \"" + role + "\"}");
	        } else {
	            out.print("{\"success\": false, \"message\": \"Chưa đăng nhập!\"}");
	        }

	        out.flush();
	        out.close();
	    }
	}