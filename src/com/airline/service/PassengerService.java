package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.airline.models.Flight;
import com.airline.models.Passenger;

/**
 * Session Bean implementation class PassengerService
 */
@Stateless
@LocalBean
//LocalBean limits the acces to this bean to the local application (ie no access from remote computer)
//Inject this bean into the interface without requiring an interface

public class PassengerService {

	/**
	 * Default constructor.
	 */
	public PassengerService() {
		// TODO Auto-generated constructor stub
	}

	// Injecting the airline perstistance unit(whose name is "airline" in
	// persistance.xml) through context dependency injection,into the entity manager
	@PersistenceContext(unitName = "airline")
	private EntityManager em; // "em" allows us to communication with the DB trough its methods

	/*
	 * public void addPassenger(Passenger p) { em.persist(p); //Saving a passenger
	 * row in the Passenger table of our DB
	 * 
	 * }
	 */

	public Passenger addPassenger(Passenger p) {
		em.persist(p);
		// Saving a passenger row in the Passenger table of our DB
		return p;
	}

	public List<Passenger> getPassengers() {
		TypedQuery<Passenger> query = em.createQuery("SELECT p FROM Passenger p", Passenger.class);
		List<Passenger> pList = query.getResultList();

		return pList;
	}

	public void addFlightTicketToPassenger(String fid, String pid) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);
		Root<Passenger> pRoot = cqPassenger.from(Passenger.class);
		cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), pid));
		Passenger p = em.createQuery(cqPassenger).getSingleResult();

		CriteriaBuilder builder1 = em.getCriteriaBuilder();
		CriteriaQuery<Flight> cqFlight = builder1.createQuery(Flight.class);
		Root<Flight> fRoot = cqFlight.from(Flight.class);
		cqFlight.select(fRoot).where(builder1.equal(fRoot.get("id").as(Integer.class), fid));
		Flight f = em.createQuery(cqFlight).getSingleResult();

		List<Flight> fList = p.getFlights();
		fList.add(f);
		p.setFlights(fList);

		f.getPassengers().add(p);
	}

	public Passenger getPassenger(Integer passengerId) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);
		Root<Passenger> pRoot = cqPassenger.from(Passenger.class);
		cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), passengerId));

		Passenger p = null;
		try {
			p = em.createQuery(cqPassenger).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
		return p;

	}
	
	public Passenger updatePassenger(Integer passengerId,Passenger pUpdated) {
		
		Passenger p = em.find(Passenger.class,passengerId );
	
		if(p==null)
			return null;
		if(pUpdated.getFirstName()!=null) 
			p.setFirstName(pUpdated.getFirstName());
			//p is attached to a row in DB Passenger Table 
			//and changes to p are applied to the row on DB(as p is managed by entity manager )
		if(pUpdated.getLastName()!=null) 	
			p.setLastName(pUpdated.getLastName());
		if(pUpdated.getDob()!=null)
			p.setDob(pUpdated.getDob());
		if(pUpdated.getGender()!=null)
			p.setGender(pUpdated.getGender());
			
		
		return p;
	}
}
