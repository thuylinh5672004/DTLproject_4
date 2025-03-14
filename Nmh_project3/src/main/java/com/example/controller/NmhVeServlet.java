package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.example.model.NmhVe;
import com.example.model.NmhVeDAO;

@WebServlet("/NmhVeServlet")
public class NmhVeServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	    private NmhVeDAO nmhVeDAO;
	    private static final Logger LOGGER = Logger.getLogger(NmhVeServlet.class.getName());

	    public void init() {
	        nmhVeDAO = new NmhVeDAO();
	    }

	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        try (PrintWriter out = response.getWriter()) {
	            out.println("<html><head>");
	            out.println("<title>Danh sách vé</title>");
	            out.println("<link rel='stylesheet' href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css'>");
	            out.println("</head><body>");
	            out.println("<div class='container py-5'>");
	            out.println("<h2 class='text-center text-primary mb-4'>Danh sách Vé</h2>");
	            out.println("<div class='row'>");

	            try {
	                List<NmhVe> listVe = nmhVeDAO.getAllVe();
	                LOGGER.log(Level.INFO, "Lấy được số lượng vé: {0}", listVe.size());
	                for (NmhVe ve : listVe) {
	                    out.println("<div class='col-md-4'>");
	                    out.println("<div class='card mb-4 shadow-sm'>");
	                    out.println("<div class='card-body'>");
	                    out.println("<h5 class='card-title'>Biển số: " + ve.getNmhBienSo() + "</h5>");
	                    out.println("<p class='card-text'><strong>Loại xe:</strong> " + ve.getNmhLoaiXe() + "</p>");
	                    out.println("<p class='card-text'><strong>Thời gian vào:</strong> " + ve.getNmhThoiGianVao() + "</p>");
	                    out.println("<p class='card-text'><strong>Thời gian ra:</strong> " + ve.getNmhThoiGianRa() + "</p>");
	                    out.println("<p class='card-text'><strong>Trạng thái:</strong> " + ve.getNmhTrangThai() + "</p>");
	                    out.println("<p class='card-text'><strong>Phí gửi xe:</strong> " + ve.getNmhPhiGuiXe() + " VNĐ</p>");
	                    out.println("<a href='NmhVeServlet?action=delete&id=" + ve.getNmhId() + "' class='btn btn-danger'>Xóa</a>");
	                    out.println("<a href='NmhVeServlet?action=edit&id=" + ve.getNmhId() + "' class='btn btn-primary'>Sửa</a>");
	                    out.println("</div></div></div>");
	                }
	            } catch (Exception e) {
	                LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách vé", e);
	                out.println("<p class='text-danger'>Lỗi khi tải danh sách vé. Vui lòng thử lại sau.</p>");
	            }

	            out.println("</div></div>");
	            out.println("<script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js'></script>");
	            out.println("</body></html>");
	        }
	    }
	}
