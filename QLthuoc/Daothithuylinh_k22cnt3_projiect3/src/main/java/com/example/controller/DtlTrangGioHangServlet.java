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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
import com.example.model.DtlDBConnect;

@WebServlet("/DtlTrangGioHangServlet")
public class DtlTrangGioHangServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        Map<String, Integer> gioHang = (Map<String, Integer>) session.getAttribute("gioHang");
	        if (gioHang == null) {
	            gioHang = new LinkedHashMap<>();
	        }
	        response.setContentType("text/html;charset=UTF-8");

	        try (Connection connection = DtlDBConnect.getConnection();
	             PrintWriter out = response.getWriter()) {

	            out.println("<!DOCTYPE html>");
	            out.println("<html lang='vi'>");
	            out.println("<head>");
	            out.println("<meta charset='UTF-8'>");
	            out.println("<title>Giỏ Hàng</title>");
	            out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
	            out.println("</head>");
	            out.println("<body>");
	            out.println("<div class='container'>");
	            out.println("<h2>🛒 Giỏ Hàng Của Bạn</h2>");

	            if (gioHang.isEmpty()) {
	                out.println("<p class='text-center'>Giỏ hàng trống. <a href='DtlSanPhamServlet'>Tiếp tục mua sắm</a></p>");
	            } else {
	                out.println("<form action='DtlCapNhatGioHangServlet' method='post'>");
	                out.println("<table class='table table-bordered text-center'>");
	                out.println("<tr class='table-primary'><th>Mã SP</th><th>Tên sản phẩm</th><th>Giá</th><th>Số lượng</th><th>Thành tiền</th><th>Hành động</th></tr>");

	                double tongTien = 0;
	                for (Map.Entry<String, Integer> entry : gioHang.entrySet()) {
	                    String productID = entry.getKey();
	                    int quantity = entry.getValue();

	                    String query = "SELECT DtlTenSP, DtlGia FROM dtlsanpham WHERE DtlMaSP = ?";
	                    try (PreparedStatement ps = connection.prepareStatement(query)) {
	                        ps.setString(1, productID);
	                        try (ResultSet rs = ps.executeQuery()) {
	                            if (rs.next()) {
	                                String tenSP = rs.getString("DtlTenSP");
	                                double gia = rs.getDouble("DtlGia");
	                                double thanhTien = gia * quantity;
	                                tongTien += thanhTien;

	                                out.println("<tr>");
	                                out.println("<td>" + productID + "</td>");
	                                out.println("<td>" + tenSP + "</td>");
	                                out.println("<td>" + gia + " VND</td>");
	                                out.println("<td><input type='number' name='soLuong" + productID + "' value='" + quantity + "' min='1' class='form-control w-50 d-inline'></td>");
	                                out.println("<td>" + thanhTien + " VND</td>");
	                                out.println("<td><a href='DtlXoaSanPhamGioServlet?maSP=" + productID + "' class='btn btn-danger btn-sm'>❌ Xóa</a></td>");
	                                out.println("</tr>");
	                            }
	                        }
	                    }
	                }

	                out.println("</table>");
	                out.println("<h3 class='text-end'>Tổng tiền: <strong>" + tongTien + " VND</strong></h3>");
	                out.println("<button type='submit' class='btn btn-warning w-100'>🔄 Cập nhật giỏ hàng</button>");
	                out.println("</form>");
	                out.println("<a href='DtlThanhToanServlet' class='btn btn-success w-100 mt-3'>✔ Thanh toán</a>");
	            }
	            
	            out.println("</div>");
	            out.println("</body></html>");
	        } catch (Exception e) {
	            e.printStackTrace(response.getWriter());
	        }
	    }
	}
