package com.example.model;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Servlet implementation class NmhDBConnection
 */
@WebServlet("/NmhDBConnection")
public class NmhDBConnection extends HttpServlet {
	 private static final String URL = "jdbc:mysql://localhost:3306/nmh_k22 project3";
	    private static final String USER = "root";
	    private static final String PASSWORD = "";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
	}
