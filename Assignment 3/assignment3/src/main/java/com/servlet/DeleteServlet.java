package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.HibernateUtil;
import com.dao.ProductDAO;

@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId = Integer.parseInt(request.getParameter("id"));
		ProductDAO productDAO = new ProductDAO(HibernateUtil.getSessionFactory());
		productDAO.deleteProduct(productId);
		System.out.println("Product Deleted");
		request.setAttribute("message", "Product Deleted Succcessfully");
		request.getRequestDispatcher("Dashboard.jsp").forward(request, response);
	}
}