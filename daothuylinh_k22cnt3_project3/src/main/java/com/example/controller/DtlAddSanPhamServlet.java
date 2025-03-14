package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.example.model.DtlDBConnect;
/**
 * Servlet implementation class DtlAddSanPhamServlet
 */
@WebServlet("/DtlAddSanPhamServlet")
public class DtlAddSanPhamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.sendRedirect("Dtladd_product.html"); 
    }

    private char[] getAddProductForm() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dtlMaSP = request.getParameter("dtlMaSP");
        String dtlTenSP = request.getParameter("dtlTenSP");
        String dtlMoTa = request.getParameter("dtlMoTa");
        double dtlGia = Double.parseDouble(request.getParameter("dtlGia"));
        int dtlSoLuong = Integer.parseInt(request.getParameter("dtlSoLuong"));
        String dtlHinhAnh = request.getParameter("dtlHinhAnh");

        try (Connection connection = DtlDBConnect.getConnection()) {
            String query = "INSERT INTO dtlsanpham (dtlMaSP, dtlTenSP, dtlMoTa, dtlGia, dtlSoLuong, dtlHinhAnh) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, dtlMaSP);
            statement.setString(2, dtlTenSP);
            statement.setString(3, dtlMoTa);
            statement.setDouble(4, dtlGia);
            statement.setInt(5, dtlSoLuong);
            statement.setString(6, dtlHinhAnh);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                response.sendRedirect("DtlSanPhamServlet?success=1"); // Chuyển hướng về danh sách sản phẩm
            } else {
                response.getWriter().println("<h3>Thêm sản phẩm thất bại!</h3>");
            }
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
   }