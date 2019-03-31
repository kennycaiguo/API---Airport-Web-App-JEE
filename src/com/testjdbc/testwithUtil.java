package com.testjdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.airline.models.Department;
import com.airline.models.Employee;
import com.airline.models.Gender;
import com.util.HibernateUtil;

public class testwithUtil {

	public static void main(String[] args) {
		
		SessionFactory factory= HibernateUtil.currentSession();
		Session session = factory.getCurrentSession();
		
		//create the object
		//Department d=new Department("dep mang");
		//Employee e = new Employee("wass","hj", Gender.Male,null,"131313", "sd@sd.co",d);
		
		//start 
		session.beginTransaction();
		
		//save
		//session.save(d);
		//session.save(e);
		
		Department d1=session.get(Department.class, 1052);
		System.out.println("Department : "+d1.getName()+"\n");
		System.out.println(d1.getEmployees());
		//commit
		session.getTransaction().commit();
		
		
	}
}
