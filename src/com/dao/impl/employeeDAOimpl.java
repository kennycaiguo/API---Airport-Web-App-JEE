package com.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.airline.models.Department;
import com.airline.models.Employee;
import com.dao.employeeDAO;
import com.util.HibernateUtil;

public class employeeDAOimpl extends GenericDAOimpl implements employeeDAO {
	private SessionFactory factory;
	private Session session;
	
	public void saveWithDep(Employee e ,Integer numDep) {
		factory= HibernateUtil.currentSession();
		session=factory.getCurrentSession();
		Department d =session.get(Department.class, numDep);
		e.setDep(d);
		save(e);
	}

}
