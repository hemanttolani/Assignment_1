package com.dao;


import java.util.List;

import org.hibernate.query.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.connection.HibernateUtil;
import com.entity.Products;
import org.hibernate.Transaction;

public class ProductDAO {

	private SessionFactory sessionFactory;
	public ProductDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("deprecation")
	public boolean addProduct(Products p) {
		boolean flag = false;
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		int i = (Integer)session.save(p);
		if(i > 0) {
			flag = true;
		}
		tx.commit();
		session.close();
		return flag;
	}

	
	public boolean deleteProduct(int id) {
		Session s = this.sessionFactory.openSession();
		Transaction tx = s.beginTransaction();
		@SuppressWarnings({ "deprecation", "rawtypes" })
		Query theQuery = s.createQuery("delete from Products where id=:productId");
		theQuery.setParameter("productId", id);
		theQuery.executeUpdate();	
		tx.commit();
		s.close();
		return false;
	}
	
	public Products getProductById(int productId) {
	    Session session = sessionFactory.openSession();
	    Products product = null;
	    try {
	        session.beginTransaction();
	        product = (Products) session.get(Products.class, productId);
	        session.getTransaction().commit();
	    } catch (HibernateException e) {
	        session.getTransaction().rollback();
	        e.printStackTrace();
	    }
	    session.close();
	    return product;
	}

	
	public List<Products> getAllProducts() {
		Session s = this.sessionFactory.openSession();
		@SuppressWarnings({ "deprecation", "rawtypes" })
		Query query = s.createQuery("from Products");
		@SuppressWarnings("unchecked")
		List<Products> list = query.list();
		s.close();
		return list;
	}
	
	public List<Products> getAllProductsById(int cid) {
		Session s = this.sessionFactory.openSession();
		@SuppressWarnings({ "deprecation", "unchecked" })
		Query<Products> query = s.createQuery("from Products as p where p.category.categoryId=:id");
		query.setParameter("id", cid);
		List<Products> list = query.list();
		s.close();
		return list;
	}
	
	
	@SuppressWarnings("deprecation")
	public void updateProduct(Products products) {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    try {
	        transaction = session.beginTransaction();
	        Products oldProduct = session.get(Products.class, products.getId());
	        session.evict(oldProduct);
	        products.setVersion(oldProduct.getVersion());
	        session.update(products);
	        transaction.commit();
	    } catch (HibernateException e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	}

	/*@SuppressWarnings("deprecation")
	public void updateProduct(int id, String title, String quantity, String size) {
		 Transaction transaction = null;
		    try (Session session = sessionFactory.openSession()) {
		        transaction = session.beginTransaction();
		        Products product = session.get(Products.class, id);
		        if (product != null) {
		            product.setTitle(title);
		            product.setQuantity(quantity);
		            product.setSize(size);
		           // product.setImage(image);
		            session.update(product);
		            transaction.commit();
		        }
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		    }
		}*/
}