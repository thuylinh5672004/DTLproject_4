package com.example;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import java.sql.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ListBooksServlet
 */
@WebServlet("/ListBooksServlet")
public class ListBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (Connection connection = DBConnection.getConnection();
             PrintWriter out = response.getWriter()) {

            // Khởi tạo HTML trả về
            out.println("<!DOCTYPE html>");
            out.println("<html lang='en'>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>List of Books</title>");
            out.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
            out.println("<style>");
            out.println("body { background-color: #f0f8ff; font-family: Arial, sans-serif; }");
            out.println(".table { background-color: #ffffff; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }");
            out.println(".table th { background-color: #007bff; color: white; }");
            out.println(".table td { background-color: #f9f9f9; color: #333; }");
            out.println(".btn-warning { background-color: #ff9800; color: white; }");
            out.println(".btn-warning:hover { background-color: #f57c00; }");
            out.println(".btn-danger { background-color: #f44336; color: white; }");
            out.println(".btn-danger:hover { background-color: #d32f2f; }");
            out.println(".btn-success { background-color: #4caf50; color: white; }");
            out.println(".btn-success:hover { background-color: #388e3c; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container mt-5'>");
            out.println("<h1 class='text-center mb-4'>List of Books</h1>");
            out.println("<table class='table table-striped table-bordered'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Title</th>");
            out.println("<th>Author</th>");
            out.println("<th>Published Year</th>");
            out.println("<th>Actions</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");

            // Lấy dữ liệu từ cơ sở dữ liệu
            String query = "SELECT * FROM books";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // Hiển thị từng dòng dữ liệu
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                out.println("<tr>");
                out.println("<td>" + id + "</td>");
                out.println("<td>" + resultSet.getString("title") + "</td>");
                out.println("<td>" + resultSet.getString("author") + "</td>");
                out.println("<td>" + resultSet.getInt("published_year") + "</td>");
                out.println("<td>");
                out.println("<form action='UpdateBookServlet' method='get' style='display:inline;'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("<button type='submit' class='btn btn-warning btn-sm'>Edit</button>");
                out.println("</form>");
                out.println("<form action='DeleteBookServlet' method='post' style='display:inline;'>");
                out.println("<input type='hidden' name='id' value='" + id + "'>");
                out.println("<button type='submit' class='btn btn-danger btn-sm'>Delete</button>");
                out.println("</form>");
                out.println("</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("<a href='AddBookServlet' class='btn btn-success'>Add New Book</a>");
            out.println("</div>");
            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js'></script>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
