package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.sql.*;
import com.example.model.DtlDBConnect;
/**
 * Servlet implementation class DtlTrangGioHangServlet
 */
@WebServlet("/DtlTrangGioHangServlet")
public class DtlTrangGioHangServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        Map<String, Integer> gioHang = (Map<String, Integer>) session.getAttribute("gioHang");

	        response.setContentType("text/html;charset=UTF-8");

	        try (PrintWriter out = response.getWriter()) {
	            out.println("<html><head><title>Giỏ hàng</title>");

	            // ✅ CSS trực tiếp trong Servlet
	            out.println("<style>");
	            out.println("body { font-family: Arial, sans-serif; padding: 20px; }");
	            out.println(".table { width: 100%; border-collapse: collapse; margin-top: 20px; }");
	            out.println(".table th, .table td { padding: 10px; border: 1px solid #ddd; text-align: center; }");
	            out.println(".table th { background: #343a40; color: white; }");
	            out.println(".btn-danger, .btn-success { padding: 5px 10px; border-radius: 5px; cursor: pointer; }");
	            out.println(".btn-danger { background: #dc3545; color: white; border: none; }");
	            out.println(".btn-success { background: #28a745; color: white; border: none; width: 100%; padding: 10px; font-size: 16px; }");
	            out.println("input[type='number'] { width: 60px; text-align: center; padding: 5px; border-radius: 5px; border: 1px solid #ddd; }");
	            out.println(".text-end { text-align: right; font-size: 20px; font-weight: bold; color: green; margin-top: 10px; }");
	            out.println("</style>");

	            // ✅ JavaScript AJAX cập nhật số lượng
	            out.println("<script>");
	            out.println("function capNhatSoLuong(maSP, soLuong) {");
	            out.println("    if (soLuong < 1) { alert('Số lượng phải lớn hơn 0!'); return; }");
	            out.println("    fetch('DtlTrangGioHangServlet', {");
	            out.println("        method: 'POST',");
	            out.println("        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },");
	            out.println("        body: 'maSP=' + maSP + '&soLuongMoi=' + soLuong");
	            out.println("    }).then(response => response.text()).then(data => {");
	            out.println("        document.body.innerHTML = data;"); // Cập nhật trang
	            out.println("    });");
	            out.println("}");
	            out.println("</script>");
	            out.println("</head><body>");

	            // ✅ Hiển thị nội dung giỏ hàng
	            if (gioHang == null || gioHang.isEmpty()) {
	                out.println("<p class='text-center text-danger'>Giỏ hàng của bạn đang trống.</p>");
	            } else {
	                try (Connection connection = DtlDBConnect.getConnection()) {
	                    double tongTien = 0;
	                    out.println("<table class='table'><thead><tr>");
	                    out.println("<th>Tên sản phẩm</th><th>Giá</th><th>Số lượng</th><th>Thành tiền</th><th>Hành động</th>");
	                    out.println("</tr></thead><tbody>");

	                    for (Map.Entry<String, Integer> entry : gioHang.entrySet()) {
	                        String dtlmaSP = entry.getKey();
	                        int dtlsoLuong = entry.getValue();

	                        String query = "SELECT dtlTenSP, dtlGia FROM dtlsanpham WHERE dtlMaSP = ?";
	                        PreparedStatement ps = connection.prepareStatement(query);
	                        ps.setString(1, dtlmaSP);
	                        ResultSet rs = ps.executeQuery();

	                        if (rs.next()) {
	                            String dtltenSP = rs.getString("dtlTenSP");
	                            double dtlgia = rs.getDouble("dtlGia");
	                            double dtlthanhTien = dtlgia * dtlsoLuong;
	                            tongTien += dtlthanhTien;

	                            out.println("<tr>");
	                            out.println("<td>" + dtltenSP + "</td>");
	                            out.println("<td>" + dtlgia + " VND</td>");
	                            out.println("<td><input type='number' value='" + dtlsoLuong + "' min='1' onchange=\"capNhatSoLuong('" + dtlmaSP + "', this.value)\"></td>");
	                            out.println("<td>" + dtlthanhTien + " VND</td>");
	                            out.println("<td><button class='btn btn-danger' onclick=\"capNhatSoLuong('" + dtlmaSP + "', 0)\">Xóa</button></td>");
	                            out.println("</tr>");
	                        }
	                    }

	                    out.println("</tbody></table>");
	                    out.println("<h3 class='text-end'>Tổng tiền: <strong>" + tongTien + " VND</strong></h3>");
	                    out.println("<form action='DtlThanhToan.html' method='post'><button type='submit' class='btn btn-success'>✔ Thanh toán</button></form>");
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    out.println("<p class='text-center text-danger'>Lỗi khi tải giỏ hàng.</p>");
	                }
	            }
	            out.println("</body></html>");
	        }
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        Map<String, Integer> gioHang = (Map<String, Integer>) session.getAttribute("gioHang");

	        String maSP = request.getParameter("maSP");
	        int soLuongMoi = Integer.parseInt(request.getParameter("soLuongMoi"));

	        if (gioHang != null) {
	            if (soLuongMoi > 0) {
	                gioHang.put(maSP, soLuongMoi);
	            } else {
	                gioHang.remove(maSP);
	            }
	        }

	        session.setAttribute("gioHang", gioHang);
	        doGet(request, response); // Tải lại trang để hiển thị giỏ hàng mới
	    }
	}