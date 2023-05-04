package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.entity.Employee;

import org.hibernate.query.*;
import org.hibernate.Transaction;


public class EmployeeDAO {
	
	private SessionFactory sessionFactory;
	
	public EmployeeDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("deprecation")
	public boolean saveEmp(Employee employee) {
		boolean flag = false;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int i = (Integer)session.save(employee);
		if(i > 0) {
			flag = true;
		}
		tx.commit();
		
		session.close();
		
		return flag;
	}
	
		public Employee getUserByEmailAndPassword(String email, String password) {
			Employee user = null;
			try {
				String query = "from Employee where email=:e and password=:p";
				Session session = sessionFactory.openSession();
				@SuppressWarnings({ "deprecation", "rawtypes" })
				Query q = session.createQuery(query);
				q.setParameter("e", email);
				q.setParameter("p", password);
				user = (Employee) q.uniqueResult();
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return user;
	}
}