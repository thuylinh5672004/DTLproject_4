package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Xử lý yêu cầu GET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Chuyển hướng người dùng tới trang thêm sách
        response.sendRedirect("Addbook.html");
    }

    // Xử lý yêu cầu POST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Nhận dữ liệu từ form
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int publishedYear = Integer.parseInt(request.getParameter("published_year"));

        try (Connection connection = DBConnection.getConnection()) {
            // Thực hiện câu lệnh SQL để thêm sách mới
            String query = "INSERT INTO books (title, author, published_year) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setInt(3, publishedYear);

            statement.executeUpdate();

            // Chuyển hướng về danh sách sách sau khi thêm thành công
            response.sendRedirect("ListBooksServlet");
        } catch (Exception e) {
            // Hiển thị lỗi nếu xảy ra
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().println("<h3>Error: " + e.getMessage() + "</h3>");
            e.printStackTrace(response.getWriter());
        }
    }
}
