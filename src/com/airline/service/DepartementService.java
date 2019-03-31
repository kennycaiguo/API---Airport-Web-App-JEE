package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Department;
import com.airline.models.Employee;
import com.airline.models.Flight;

@Stateless
@LocalBean
public class DepartementService {
	
	@PersistenceContext(unitName="airline")
	EntityManager em;
	

	public DepartementService() {
	}


	public void addDepartement(Department d) {
		
		em.persist(d);
	}
	
	//Method to be called post employee persistance
	public void addEmployeeToDepartement(Employee e,String depid) {
		
		TypedQuery<Department> dQuery = em.createNamedQuery("Department.findById", Department.class);
		// createNamedQuery ..?we're not creating a Query , we're just using the
		// existent one (in Departement.java Model)
		dQuery.setParameter("id", Integer.parseInt(depid));
		Department d = dQuery.getSingleResult();// get a departement "d" whose id equals departementid
		
		/*List<Employee> eList=d.getEmployees();
		eList.add(e);
		d.setEmployees(eList);
		e.setDep(d);*/
		

	}

}
