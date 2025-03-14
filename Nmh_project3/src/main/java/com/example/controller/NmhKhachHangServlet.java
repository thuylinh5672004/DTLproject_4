package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.example.model.NmhKhachHang;
import com.example.model.NmhKhachHangDAO;
/**
 * Servlet implementation class NmhKhachHangServlet
 */
@WebServlet("/NmhKhachHangServlet")
public class NmhKhachHangServlet extends HttpServlet {
	  private static final long serialVersionUID = 1L;
	    private NmhKhachHangDAO khachHangDAO;

	    public void init() {
	        khachHangDAO = new NmhKhachHangDAO();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        List<NmhKhachHang> listKhachHang = null;
			try {
				listKhachHang = khachHangDAO.getAllKhachHang();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        request.setAttribute("listKhachHang", listKhachHang);
	        request.getRequestDispatcher("khachhang.html").forward(request, response);
	    }
	}