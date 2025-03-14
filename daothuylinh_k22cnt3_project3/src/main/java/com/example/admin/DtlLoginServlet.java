package com.example.admin;

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
 * Servlet implementation class DtlLoginServlet
 */
@WebServlet("/DtlLoginServlet")
public class DtlLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DtlTaikhoanDAO tkDAO = new DtlTaikhoanDAO();
        DtlTaikhoan tk = tkDAO.login(username, password);

        String jsonResponse;
        if ("admin".equals(username) && "12345".equals(password)) {
            // Tạo session cho user
            HttpSession session = request.getSession();
            session.setAttribute("username", tk.getDtlTenDangNhap());
            session.setAttribute("role", tk.getDtlLoaiTK());

            jsonResponse = "{\"success\": true, \"role\": \"" + tk.getDtlLoaiTK() + "\"}";
        } else {
            jsonResponse = "{\"success\": false, \"message\": \"Sai tài khoản hoặc mật khẩu!\"}";
        }

        out.print(jsonResponse);
        out.flush();
    }
}