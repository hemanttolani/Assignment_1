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

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Employee employee = new Employee(name, email, password);
		System.out.println(employee);
		EmployeeDAO employeeDAO  = new EmployeeDAO(HibernateUtil.getSessionFactory());
		boolean flag = employeeDAO.saveEmp(employee);
		HttpSession session = req.getSession();
		if(flag == true) {
			session.setAttribute("msg","Employee Registered Successfully" );
			System.out.println("Employee Registered Successfully");
		}
		else {
			session.setAttribute("msg","Something Went Wrong On Server");
			System.out.println("Something Went Wrong On Server");
		}
		resp.sendRedirect("index.jsp");
	}
}