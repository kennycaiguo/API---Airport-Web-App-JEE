package com.testjdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.airline.models.Department;
import com.airline.models.Employee;
import com.airline.models.Gender;
import com.util.HibernateUtil;


public class test {

	
	
	public static void main(String[] args) {
		
		SessionFactory factory= new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Department.class)
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();		
		
		try {
			//Department d=new Department("dep mang");
			//Employee e = new Employee("wass","hj", Gender.Male,null,"131313", "sd@sd.co",d);
			
			System.out.println("test1");
			//start the transaction
			session.beginTransaction();
			//save
			//session.save(d);
			//session.save(e);
			Department d1=session.get(Department.class, 652);
			System.out.println("Department : "+d1.getName()+"\n");
			System.out.println(d1.getEmployees());
			//System.out.println(d1.getEmployees());
			//commit
			session.getTransaction().commit();
			
			
		}finally {
			//factory.close();
		}

	}

}
