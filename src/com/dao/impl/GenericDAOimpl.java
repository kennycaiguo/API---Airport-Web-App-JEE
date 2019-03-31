package com.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.util.HibernateUtil;

public class GenericDAOimpl {
	
	private SessionFactory factory;
	private Session session;
	 
	public GenericDAOimpl() {
		//get session from HibernateUtil
		
		
		

	}
	
	public void save(Object entity) {
		
		try {
			
			factory= HibernateUtil.currentSession();
			session=factory.getCurrentSession();
			//start the transaction
			session.beginTransaction();
			//save
			session.save(entity);
			//commit
			session.getTransaction().commit();
			
			
		}
		finally{
			factory.close();
		}
		
	}
	
public void delete(Object entity) {
		
		try {

			//start the transaction
			session.beginTransaction();
			//save
			session.delete(entity);
			//commit
			session.getTransaction().commit();
			
		}
		finally{
			factory.close();
		}
		
	}
	
	

}
