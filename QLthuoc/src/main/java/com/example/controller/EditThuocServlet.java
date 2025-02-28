package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.example.model.DBThuocDao;

import java.sql.*;
/**
 * Servlet implementation class EditThuocServlet
 */
@WebServlet("/EditThuocServlet")
public class EditThuocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String username = null;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("username".equals(cookie.getName())) {
                    username = cookie.getValue();
                    break;
                }
            }
        }

        int id = Integer.parseInt(request.getParameter("id"));

        response.setContentType("text/html;charset=UTF-8");

        try (Connection connection = DBThuocDao.getConnection(); PrintWriter out = response.getWriter()) {
            // Query the drug by ID
            String query = "SELECT * FROM thuoc_daothithuylinh WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String tenThuoc = resultSet.getString("TenThuoc");
                Date ngayNhap = resultSet.getDate("NgayNhap");
                String loaiThuoc = resultSet.getString("LoaiThuoc");
                int soLuong = resultSet.getInt("SoLuong");
                double donGia = resultSet.getDouble("DonGia");
                String anhThuoc = resultSet.getString("AnhThuoc");

                // Render HTML form for editing drug details
                out.println("<!DOCTYPE html>");
                out.println("<html lang='vi'>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<title>Cập Nhật Thuốc</title>");
                out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
                out.println("</head>");
                out.println("<body>");
                out.println("<div class='container mt-5'>");

                out.println("<h2>Cập Nhật Thông Tin Thuốc</h2>");
                out.println("<form method='POST' action='EditThuocServlet'>");
                out.println("<div class='mb-3'>");
                out.println("<label for='id' class='form-label'>Mã Thuốc</label>");
                out.println("<input type='text' class='form-control' id='id' name='id' value='" + id + "' readonly>");
                out.println("</div>");
                out.println("<div class='mb-3'>");
                out.println("<label for='tenThuoc' class='form-label'>Tên Thuốc</label>");
                out.println("<input type='text' class='form-control' id='tenThuoc' name='tenThuoc' value='" + tenThuoc + "'>");
                out.println("</div>");
                out.println("<div class='mb-3'>");
                out.println("<label for='ngayNhap' class='form-label'>Ngày Nhập</label>");
                out.println("<input type='date' class='form-control' id='ngayNhap' name='ngayNhap' value='" + (ngayNhap != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(ngayNhap) : "") + "'>");
                out.println("</div>");
                out.println("<div class='mb-3'>");
                out.println("<label for='loaiThuoc' class='form-label'>Loại Thuốc</label>");
                out.println("<input type='text' class='form-control' id='loaiThuoc' name='loaiThuoc' value='" + loaiThuoc + "'>");
                out.println("</div>");
                out.println("<div class='mb-3'>");
                out.println("<label for='soLuong' class='form-label'>Số Lượng</label>");
                out.println("<input type='number' class='form-control' id='soLuong' name='soLuong' value='" + soLuong + "'>");
                out.println("</div>");
                out.println("<div class='mb-3'>");
                out.println("<label for='donGia' class='form-label'>Đơn Giá (VND)</label>");
                out.println("<input type='number' class='form-control' id='donGia' name='donGia' value='" + donGia + "' step='0.01'>");
                out.println("</div>");
                out.println("<div class='mb-3'>");
                out.println("<label for='anhThuoc' class='form-label'>Ảnh Thuốc</label>");
                out.println("<input type='text' class='form-control' id='anhThuoc' name='anhThuoc' value='" + anhThuoc + "'>");
                out.println("</div>");
                out.println("<button type='submit' class='btn btn-primary'>Cập Nhật</button>");
                out.println("</form>");

                out.println("</div>");
                out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js'></script>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("Không tìm thấy thuốc!");
            }
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tenThuoc = request.getParameter("tenThuoc");
        String ngayNhapStr = request.getParameter("ngayNhap");
        String loaiThuoc = request.getParameter("loaiThuoc");
        int soLuong = Integer.parseInt(request.getParameter("soLuong"));
        double donGia = Double.parseDouble(request.getParameter("donGia"));
        String anhThuoc = request.getParameter("anhThuoc");

        try (Connection connection = DBThuocDao.getConnection()) {
            String query = "UPDATE thuoc_daothithuylinh SET TenThuoc = ?, NgayNhap = ?, LoaiThuoc = ?, SoLuong = ?, DonGia = ?, AnhThuoc = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, tenThuoc);
            statement.setDate(2, java.sql.Date.valueOf(ngayNhapStr));
            statement.setString(3, loaiThuoc);
            statement.setInt(4, soLuong);
            statement.setDouble(5, donGia);
            statement.setString(6, anhThuoc);
            statement.setInt(7, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("ThuocServlet");
            } else {
                response.getWriter().write("Lỗi khi cập nhật thuốc!");
            }
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}