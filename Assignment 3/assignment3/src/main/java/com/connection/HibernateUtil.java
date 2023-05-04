package com.connection;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import com.entity.*;

public class HibernateUtil {
	
	private static SessionFactory sf;
	public static SessionFactory getSessionFactory() {
		
		if(sf == null) {
			Configuration cf = new Configuration();
			Properties p = new Properties();
			p.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
			p.put(Environment.URL, "jdbc:mysql://localhost:3306/assignment3");
			p.put(Environment.USER, "root");
			p.put(Environment.PASS, "root");
			p.put(Environment.DIALECT,"org.hibernate.dialect.MySQL8Dialect");
			p.put(Environment.HBM2DDL_AUTO, "update");
			p.put(Environment.SHOW_SQL, "true");
			cf.setProperties(p);
			cf.addAnnotatedClass(Employee.class);
			cf.addAnnotatedClass(Products.class);
			ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(cf.getProperties()).build();
			sf = cf.buildSessionFactory(sr);
		}
		
		return sf;
	}

}
