package com.airline.controllers;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.EJB;
import javax.persistence.OneToOne;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.flightDestinations;
import com.airline.service.FlightService;

/**
 * Servlet implementation class AddFlight
 */
@WebServlet("/AddFlight")
public class AddFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	FlightService fs;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddFlight() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// response.getWriter().append("Served at: ").append(request.getContextPath());

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		
		Flight f = new Flight();
		
		String from_destination=request.getParameter("from_destination");
		f.setFlightOrigin(flightDestinations.valueOf(from_destination));
		
		f.setFlightDestination(flightDestinations.valueOf(request.getParameter("to_destination")));
		
		f.setPrice(Integer.parseInt(request.getParameter("price")));
		
		Integer year = Integer.parseInt(request.getParameter("year"));
		Integer month = Integer.parseInt(request.getParameter("month"));
		Integer day = Integer.parseInt(request.getParameter("day"));
		Integer hour = Integer.parseInt(request.getParameter("hour"));
		Integer minute = Integer.parseInt(request.getParameter("minute"));
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DATE, day);
		cal.set(Calendar.HOUR, hour);
		cal.set(Calendar.MINUTE, minute);
		Date flightTime = cal.getTime();
		f.setFlightTime(flightTime);

		System.out.println("flight itme : " + flightTime);

		Airplane a = new Airplane();
		
		a.setModelName(request.getParameter("airplane_model"));
		a.setPlaneMake(request.getParameter("airplane_make"));
		a.setSeatingCapacity(Integer.parseInt(request.getParameter("airplane_seating")));

		f.setAirplaneDetail(a); 
		// connected the flight with airplane
		//no need to do : a.setFlight(f); we didn't even put the setter for it , we just used it to do " @OneToOne(mappedBy="airplaneDetail") "
		System.out.println(f);
		System.out.println(a);
		
		fs.addFlight(f, a);
		
		response.sendRedirect("Flights");
	}

}
