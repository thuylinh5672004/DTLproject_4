package com.example.controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.example.model.DtlDBConnect;

/**
 * Servlet implementation class EditProductServlet
 */
@WebServlet("/EditProductServlet")
public class DtlEditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maSP = request.getParameter("MaSP");

        response.setContentType("text/html;charset=UTF-8");
        try (Connection connection = DtlDBConnect.getConnection();
             PrintWriter out = response.getWriter()) {

            String query = "SELECT * FROM sanpham WHERE MaSP=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String tenSP = rs.getString("TenSP");
                String moTa = rs.getString("MoTa");
                double gia = rs.getDouble("Gia");
                int soLuong = rs.getInt("SoLuong");
                String hinhAnh = rs.getString("HinhAnh");

                out.println("<!DOCTYPE html>");
                out.println("<html lang='vi'>");
                out.println("<head><meta charset='UTF-8'><title>Chỉnh sửa sản phẩm</title></head>");
                out.println("<body>");
                out.println("<h2>Chỉnh sửa sản phẩm</h2>");
                out.println("<form action='UpdateProductServlet' method='post'>");
                out.println("Mã SP: <input type='text' name='MaSP' value='" + maSP + "' readonly><br>");
                out.println("Tên SP: <input type='text' name='TenSP' value='" + tenSP + "'><br>");
                out.println("Mô tả: <textarea name='MoTa'>" + moTa + "</textarea><br>");
                out.println("Giá: <input type='number' step='0.01' name='Gia' value='" + gia + "'><br>");
                out.println("Số lượng: <input type='number' name='SoLuong' value='" + soLuong + "'><br>");
                out.println("Hình ảnh URL: <input type='text' name='HinhAnh' value='" + hinhAnh + "'><br>");
                out.println("<button type='submit'>Cập nhật</button>");
                out.println("</form>");
                out.println("</body></html>");
            } else {
                out.println("<p>Sản phẩm không tồn tại.</p>");
            }

        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
