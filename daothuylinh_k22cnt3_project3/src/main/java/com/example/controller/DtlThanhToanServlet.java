package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

import com.example.model.DtlDBConnect;
/**
 * Servlet implementation class DtlThanhToanServlet
 */
@WebServlet("/DtlThanhToanServlet")
public class DtlThanhToanServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        Map<String, Integer> gioHang = (Map<String, Integer>) session.getAttribute("gioHang");

	        // Nếu giỏ hàng chưa có trong session, tạo mới
	        if (gioHang == null) {
	            gioHang = new LinkedHashMap<>();
	            session.setAttribute("gioHang", gioHang);
	        }

	        // Lấy mã sản phẩm từ request (nếu có)
	        String maSP = request.getParameter("maSP");
	        if (maSP != null && !maSP.isEmpty()) {
	            gioHang.put(maSP, gioHang.getOrDefault(maSP, 0) + 1);
	            session.setAttribute("gioHang", gioHang);
	        }

	        // Nếu giỏ hàng rỗng thì quay lại trang sản phẩm
	        if (gioHang.isEmpty()) {
	            response.sendRedirect("DtlSanPhamServlet");
	            return;
	        }

	        response.setContentType("text/html;charset=UTF-8");

	        try (PrintWriter out = response.getWriter();
	             Connection connection = DtlDBConnect.getConnection()) {

	            // Bắt đầu HTML
	            out.println("<!DOCTYPE html>");
	            out.println("<html lang='vi'>");
	            out.println("<head>");
	            out.println("<meta charset='UTF-8'>");
	            out.println("<meta name='viewport' content='width=device-width, initial-scale=1'>");
	            out.println("<title>Xác nhận thanh toán</title>");
	            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css' rel='stylesheet'>");
	            out.println("<style>");
	            out.println("body { background-color: #f8f9fa; font-family: Arial, sans-serif; }");
	            out.println(".container { max-width: 800px; margin-top: 50px; }");
	            out.println(".table th, .table td { text-align: center; vertical-align: middle; }");
	            out.println(".btn-custom { padding: 10px 20px; font-size: 18px; border-radius: 8px; }");
	            out.println(".btn-secondary:hover { background-color: #6c757d; }");
	            out.println(".btn-success:hover { background-color: #218838; }");
	            out.println("</style>");
	            out.println("</head>");
	            out.println("<body>");

	            // Thanh điều hướng
	            out.println("<nav class='navbar navbar-dark bg-dark'><div class='container'>");
	            out.println("<a class='navbar-brand text-light' href='DtlSanPhamServlet'>Trang chủ</a>");
	            out.println("</div></nav>");

	            // Bắt đầu nội dung chính
	            out.println("<div class='container'>");
	            out.println("<h2 class='text-center text-primary mb-4'>Xác nhận thanh toán</h2>");
	            out.println("<table class='table table-bordered table-hover bg-white shadow-sm rounded'>");
	            out.println("<thead class='table-dark'><tr><th>Sản phẩm</th><th>Số lượng</th><th>Giá</th><th>Tổng</th></tr></thead><tbody>");

	            double tongTien = 0;

	            // Duyệt qua giỏ hàng
	            for (Map.Entry<String, Integer> entry : gioHang.entrySet()) {
	                String dtlmaSP = entry.getKey();
	                int soLuong = entry.getValue();

	                // Truy vấn thông tin sản phẩm từ cơ sở dữ liệu
	                try (PreparedStatement statement = connection.prepareStatement("SELECT dtlTenSP, dtlGia FROM dtlsanpham WHERE dtlMaSP = ?")) {
	                    statement.setString(1, dtlmaSP);
	                    ResultSet resultSet = statement.executeQuery();

	                    if (resultSet.next()) {
	                        String dtltenSP = resultSet.getString("dtlTenSP");
	                        double dtlgia = resultSet.getDouble("dtlGia");
	                        double thanhTien = dtlgia * soLuong;
	                        tongTien += thanhTien;

	                        // Hiển thị sản phẩm
	                        out.println("<tr>");
	                        out.println("<td>" + dtltenSP + "</td>");
	                        out.println("<td>" + soLuong + "</td>");
	                        out.println("<td>" + dtlgia + " VNĐ</td>");
	                        out.println("<td>" + thanhTien + " VNĐ</td>");
	                        out.println("</tr>");
	                    }
	                }
	            }

	            out.println("</tbody>");
	            out.println("<tfoot class='table-light'><tr><th colspan='3' class='text-end'>Tổng cộng:</th><th>" + tongTien + " VNĐ</th></tr></tfoot>");
	            out.println("</table>");

	            // Nút điều hướng
	            out.println("<div class='text-center mt-4'>");
	            out.println("<a href='DtlSanPhamServlet' class='btn btn-secondary btn-custom me-3'>Tiếp tục mua hàng</a>");
	            out.println("<a href='DtlThanhToan.html' class='btn btn-success btn-custom'>Xác nhận thanh toán</a>");
	            out.println("</div>");

	            out.println("</div>");
	            out.println("</body>");
	            out.println("</html>");

	        } catch (Exception e) {
	            e.printStackTrace(response.getWriter());
	        }
	    }
	}