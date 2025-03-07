package com.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DtlDBConnect {
	 private static final String URL = "jdbc:mysql://localhost:3306/daothuylinh_k22cnt3_project3";
	    private static final String USER = "root";
	    private static final String PASSWORD = "";

	    public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(URL, USER, PASSWORD);
	    }
	}


