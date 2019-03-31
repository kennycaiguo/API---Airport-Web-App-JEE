package com.airline.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.service.FlightService;
import com.airline.service.PassengerService;

/**
 * Servlet implementation class AddFlightTicketToPassenger
 */
@WebServlet("/AddFlightTicketToPassenger")
public class AddFlightTicketToPassenger extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	PassengerService ps;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFlightTicketToPassenger() {
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
	String fid=request.getParameter("fid1");
	String pid=request.getParameter("pid1");
	ps.addFlightTicketToPassenger(fid, pid);
	response.sendRedirect("Passengers");
	
	}

}
