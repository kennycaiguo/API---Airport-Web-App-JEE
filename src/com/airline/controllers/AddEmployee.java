package com.airline.controllers;

import java.io.IOException;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Department;
import com.airline.models.Employee;
import com.airline.models.Gender;
import com.airline.service.DepartementService;
import com.dao.employeeDAO;
import com.dao.impl.employeeDAOimpl;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	DepartementService ds;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployee() {
        super();
        // TODO Auto-generated constructor stub
        
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		System.out.println("\nPrior ...\n");
		String dob_raw = request.getParameter("emp_dob");
		System.out.println("\nPrior1 ...\n");
		String[] dobArr = dob_raw.split("\\/");
		System.out.println("\nPrior 2...\n");
		System.out.println("dobArr[0] is " +dobArr[0]+" , dobArr[1] is "+dobArr[1]+" , dobArr[2] "+dobArr[2]);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(dobArr[2]));
		cal.set(Calendar.MONTH, Integer.parseInt(dobArr[0])-1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dobArr[1]));
		
		Employee e= new Employee();
		e.setfName(request.getParameter("emp_fName"));
		e.setlName(request.getParameter("emp_lName"));
		e.setGender(Gender.valueOf(request.getParameter("emp_gender")));
		e.setEmail(request.getParameter("emp_email"));
		e.setPhone(request.getParameter("emp_phone"));
		e.setEmpDob(cal.getTime());
		
		ds.addEmployeeToDepartement(e, request.getParameter("emp_dep"));
		
		//Oracle 
		employeeDAOimpl empdap=new employeeDAOimpl();
		empdap.saveWithDep(e, Integer.parseInt(request.getParameter("emp_dep")));
		
		
	}

}
