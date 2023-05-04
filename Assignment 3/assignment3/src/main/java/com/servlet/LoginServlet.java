package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connection.HibernateUtil;
import com.dao.EmployeeDAO;
import com.entity.Employee;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		EmployeeDAO userDao = new EmployeeDAO(HibernateUtil.getSessionFactory());
		Employee user = userDao.getUserByEmailAndPassword(email, password);

		HttpSession httpSession = request.getSession();	
		if (user == null) {
			httpSession.setAttribute("message", "Invalid Credentials! Try Again");
			response.sendRedirect("Login.jsp");
			return;
		} else {
				response.sendRedirect("Dashboard.jsp");
				httpSession.setAttribute("data", user);
				httpSession.setAttribute("authenticated", true);
				return;
			}

		}

	}