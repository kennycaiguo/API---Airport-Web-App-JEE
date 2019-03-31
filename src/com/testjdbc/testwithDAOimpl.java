package com.testjdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.airline.models.Department;
import com.airline.models.Employee;
import com.airline.models.Gender;
import com.dao.departmentDAO;
import com.dao.impl.departmentDAOimpl;
import com.util.HibernateUtil;

public class testwithDAOimpl {

	public static void main(String[] args) {
	
		
		departmentDAO depdao=new departmentDAOimpl();
		

		//create the object
		Department d=new Department("dep managers123");
		depdao=new departmentDAOimpl();
		Employee e = new Employee("wass","hji", Gender.Male,null,"131313", "sd@sd.co",d);
		
		//save
		depdao.save(d);
		depdao.save(e);
				
		
				
		
	}
}
