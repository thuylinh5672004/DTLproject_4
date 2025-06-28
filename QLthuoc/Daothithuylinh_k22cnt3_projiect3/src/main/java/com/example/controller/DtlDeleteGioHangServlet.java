package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.example.model.DtlSanPham;
/**
 * Servlet implementation class DeleteGioHangServlet
 */
@WebServlet("/DtlDeleteGioHangServlet")
public class DtlDeleteGioHangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<DtlSanPham> gioHang = (List<DtlSanPham>) session.getAttribute("dtlgioHang");

        if (gioHang != null) {
            String maSP = request.getParameter("dtlmaSP");

            // Xóa sản phẩm khỏi giỏ hàng
            gioHang.removeIf(sp -> sp.getmaSP().equals(maSP));

            // Cập nhật lại session
            session.setAttribute("gioHang", gioHang);
        }

        // Chuyển hướng về trang giỏ hàng
        response.sendRedirect("DtlGioHang.html");
    }
}