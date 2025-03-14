package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.example.model.NmhBaiDoXe;
import com.example.model.NmhBaiDoXeDAO;
/**
 * Servlet implementation class NmhBaiDoXeServlet
 */
@WebServlet("/NmhBaiDoXeServlet")
public class NmhBaiDoXeServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private NmhBaiDoXeDAO baiDoXeDAO;

	    public void init() {
	        baiDoXeDAO = new NmhBaiDoXeDAO();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getParameter("action");
	        if (action == null) action = "";

	        try {
	            switch (action) {
	                case "delete":
	                    int id = Integer.parseInt(request.getParameter("id"));
	                    baiDoXeDAO.deleteBaiDoXe(id);
	                    response.sendRedirect("NmhBaiDoXeServlet");
	                    break;

	                default:
	                    List<NmhBaiDoXe> listBaiDoXe = baiDoXeDAO.getAllBaiDoXe();
	                    request.setAttribute("listBaiDoXe", listBaiDoXe);
	                    request.getRequestDispatcher("baidoxe.html").forward(request, response);
	                    break;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        String action = request.getParameter("action");

	        try {
	            if ("insert".equals(action)) {
	                String tenBai = request.getParameter("ten_bai");
	                int sucChua = Integer.parseInt(request.getParameter("suc_chua"));
	                String diaChi = request.getParameter("dia_chi");

	                baiDoXeDAO.insertBaiDoXe(new NmhBaiDoXe(0, tenBai, sucChua, diaChi));
	            }
	            response.sendRedirect("NmhBaiDoXeServlet");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
