 package com.airline.webservices.rest;


import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.airline.models.Flight;
import com.airline.models.Passenger;
import com.airline.service.PassengerService;

@Path("/Passengers")  // webservice path in this class starts with airlineservices/rest/Passengers
public class PassengersWebService {
	
	@PersistenceContext(unitName="airline")
	EntityManager em;
	
	@EJB
	PassengerService ps;
	
	@Context
	UriInfo pUriInfo; 
	
	public PassengersWebService() {
		
	}
	
	@GET // we can acces this method through a GET request(=going to the website link)
	@Produces(MediaType.APPLICATION_XML) //XML: format of data we're getting back(we're getting the List of passengers as xml)
	public List<Passenger> getPassengers(){
		
		List<Passenger> pList=ps.getPassengers();
		return pList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("{passenger_Id}") 
	//Path: means to execute the following method we should go to http://localhost:8080/jaxrs3/airlineservices/rest/Passenger/5 
	// what's /5 ??? well , 5 : is the passenger_Id we give
	public Passenger getFlight(@PathParam("passenger_Id") Integer passengerId) {
		//@PathParam("passenger_Id") : convert passenger_Id content to passengerId variable
		Passenger p =ps.getPassenger(passengerId);
		if(p==null) {
			throw new NotFoundException("the Passenger with an id of"+passengerId+"wasn't found");
		}
		return p;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	// read A JSON we provide and converts to into a Passenger object
	public Response addPassenger(Passenger p) {
		
		p=ps.addPassenger(p);
		//when we provide "p" as a JSON , it doesn't have an id 
		//it'll have one the moment it get persisted through the ps.addPassenger(p)
		UriBuilder pUriBuilder = pUriInfo.getAbsolutePathBuilder();
		URI pUri = pUriBuilder.path(String.valueOf(p.getId())).build();
		//73+74 : Creating a URl for the created Passenger
		return Response.created(pUri).build();
	}
	
	@PUT
	@Path("/edit/{pid}")
	@Consumes("application/json")
	//or @Consumes(MediaType.APPLICATION_JSON) 
	//user submit ,in JSON , the updated properties of the passenger and they'll be updated to pUpdated Object
	public Response updatePassenger(@PathParam("pid")Integer passengerId,Passenger pUpdated) {
		//pUpdated will come from JSON in the request body
		
		pUpdated=ps.updatePassenger(passengerId, pUpdated);
		if(pUpdated==null)
			throw new NotFoundException("The Passenger with an Id of "+passengerId+"wasn't found");
		
		return Response.ok(pUpdated).build();
	}
	
	

}
