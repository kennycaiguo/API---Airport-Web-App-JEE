package com.airline.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Department;
import com.airline.service.DepartementService;
import com.dao.departmentDAO;
import com.dao.impl.departmentDAOimpl;

/**
 * Servlet implementation class AddDepartement
 */
@WebServlet("/AddDepartement")
public class AddDepartement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDepartement() {
        super();
        // TODO Auto-generated constructor stub
    }
    @EJB
    DepartementService ds;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//Apache Derby work 
		Department d = new Department();
		d.setName(request.getParameter("dep_name"));
		ds.addDepartement(d);
		
		//Oracle 11g instructions
		departmentDAO depdao=new departmentDAOimpl();
		depdao.save(d);
		
		
	}

}
