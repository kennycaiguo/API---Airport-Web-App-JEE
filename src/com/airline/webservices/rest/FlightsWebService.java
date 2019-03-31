 package com.airline.webservices.rest;


import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import javax.ws.rs.core.MediaType;

import com.airline.models.Flight;
import com.airline.service.FlightService;
import com.airline.service.FlightService;

@Path("/Flights")  // webservice path in this class starts with airlineservices/rest/Flights
public class FlightsWebService {
	
	@PersistenceContext(unitName="airline")
	EntityManager em;
	
	@EJB
	FlightService fs;
	
	@Context
	UriInfo fUriInfo; 
	
	public FlightsWebService() {
		
	}
	
	@GET // we can acces this method through a GET request(=going to the website link)
	@Produces(MediaType.APPLICATION_JSON) // JSON: format of data we're getting back (we're getting the List of flights as JSON )
	public List<Flight> getFlights(){
		
		List<Flight> fList=fs.getFlights();
		return fList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{flight_Id}") 
	//Path: means to execute the following method we should go to http://localhost:8080/jaxrs3/airlineservices/rest/Flights/5
	// what's /5 ??? well , 5 : is the flight_Id we give
	public Flight getFlight(@PathParam("flight_Id") Integer flightId) {
		//@PathParam("flight_Id") : convert flight_Id content to flightId variable
		Flight f=fs.getFlight(flightId);
		if(f==null) 
			throw new NotFoundException("the Flight with an id of"+flightId+"wasn't found");

		return f;
	}

}
