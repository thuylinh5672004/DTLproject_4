package com.example;

import jakarta.servlet.ServletException;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet("/UpdateBookServlet")
public class UpdateBookServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM books WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String filePath = getServletContext().getRealPath("/update-book.html");
                String htmlContent = new String(Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8);

                htmlContent = htmlContent.replace("{{id}}", String.valueOf(id));
                htmlContent = htmlContent.replace("{{title}}", resultSet.getString("title"));
                htmlContent = htmlContent.replace("{{author}}", resultSet.getString("author"));
                htmlContent = htmlContent.replace("{{published_year}}", String.valueOf(resultSet.getInt("published_year")));

                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write(htmlContent);
            } else {
                response.getWriter().write("Không tìm thấy sách!");
            }
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int publishedYear = Integer.parseInt(request.getParameter("published_year"));

        try (Connection connection = DBConnection.getConnection()) {
            String query = "UPDATE books SET title = ?, author = ?, published_year = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setInt(3, publishedYear);
            statement.setInt(4, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("ListBooksServlet");
            } else {
                response.getWriter().write("Lỗi khi cập nhật sách!");
            }
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}