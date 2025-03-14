package com.example.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import com.example.model.DtlDBConnect;

/**
 * Servlet implementation class DtlDashboardServlet
 */
@WebServlet("/DtlDashboardServlet")
public class DtlDashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String username = (session != null) ? (String) session.getAttribute("username") : null;

        response.setContentType("text/html;charset=UTF-8");
        try (Connection connection = DtlDBConnect.getConnection();
             PrintWriter out = response.getWriter()) {

            // Truy vấn số lượng sản phẩm, đơn hàng, khách hàng
            int totalProducts = getTotalCount(connection, "dtlsanpham");
            int totalOrders = getTotalCount(connection, "dtldonhang");
            int totalCustomers = getTotalCount(connection, "dtlkhachhang");

            // Bắt đầu trang HTML
            out.println("<!DOCTYPE html>");
            out.println("<html lang='vi'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>Trang Quản trị</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("</head>");
            out.println("<body>");

            // Thanh công cụ (Navbar)
            out.println("<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>");
            out.println("<div class='container-fluid'>");
            out.println("<a class='navbar-brand' href='#'>Admin Panel</a>");
            out.println("<button class='navbar-toggler' type='button' data-bs-toggle='collapse' data-bs-target='#navbarNav'>");
            out.println("<span class='navbar-toggler-icon'></span>");
            out.println("</button>");
            out.println("<div class='collapse navbar-collapse' id='navbarNav'>");
            out.println("<ul class='navbar-nav'>");
            out.println("<li class='nav-item'><a class='nav-link' href='DtlProductServlet'>Quản lý sản phẩm</a></li>");
            out.println("<li class='nav-item'><a class='nav-link active' href='DtlDashboardServlet'>Dashboard</a></li>");
            if (username == null) {
                out.println("<li class='nav-item'><a class='nav-link' href='login.html'>Đăng nhập</a></li>");
                out.println("<li class='nav-item'><a class='nav-link' href='register.html'>Đăng ký</a></li>");
            } else {
                out.println("<li class='nav-item'><a class='nav-link' href='DtlLogoutServlet'>Đăng xuất</a></li>");
            }
            out.println("</ul>");
            out.println("</div>");
            out.println("</div>");
            out.println("</nav>");

            // Nội dung chính
            out.println("<div class='container mt-5'>");
            out.println("<h1 class='text-center mb-4'>Trang Quản trị</h1>");

            // Hiển thị thống kê
            out.println("<div class='row'>");

            out.println("<div class='col-md-4'>");
            out.println("<div class='card text-white bg-primary mb-3'>");
            out.println("<div class='card-header'>Sản phẩm</div>");
            out.println("<div class='card-body'>");
            out.println("<h5 class='card-title'>" + totalProducts + " sản phẩm</h5>");
            out.println("</div></div></div>");

            out.println("<div class='col-md-4'>");
            out.println("<div class='card text-white bg-success mb-3'>");
            out.println("<div class='card-header'>Đơn hàng</div>");
            out.println("<div class='card-body'>");
            out.println("<h5 class='card-title'>" + totalOrders + " đơn hàng</h5>");
            out.println("</div></div></div>");

            out.println("<div class='col-md-4'>");
            out.println("<div class='card text-white bg-warning mb-3'>");
            out.println("<div class='card-header'>Khách hàng</div>");
            out.println("<div class='card-body'>");
            out.println("<h5 class='card-title'>" + totalCustomers + " khách hàng</h5>");
            out.println("</div></div></div>");

            out.println("</div>"); // Đóng row

            out.println("</div>"); // Đóng container
            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }

    // Hàm lấy tổng số lượng từ bảng CSDL
    private int getTotalCount(Connection connection, String tableName) {
        int count = 0;
        String query = "SELECT COUNT(*) AS total FROM " + tableName;
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            if (resultSet.next()) {
                count = resultSet.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}