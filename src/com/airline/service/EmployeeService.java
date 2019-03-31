package com.airline.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.airline.models.Employee;

@Stateless
@LocalBean
public class EmployeeService {

	@PersistenceContext(unitName="airline")
	EntityManager em;

	public EmployeeService() {
	}
	
	public void addEmployee(Employee e) {
		em.persist(e);
	}
}
