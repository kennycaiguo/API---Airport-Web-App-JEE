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

import org.eclipse.persistence.internal.oxm.schema.model.Restriction;

import com.airline.models.Airplane;
import com.airline.models.Flight;
import com.airline.models.Passenger;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class FlightService
 */
@Stateless
@LocalBean // implements the EJB local interface
public class FlightService {

	/**
	 * Default constructor.
	 */
	public FlightService() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext(unitName = "airline") // the em uses the persistence unit to connect to the db through its
	EntityManager em; // reference (jdbc/airline : ref to the db connection name)

	public void addFlight(Flight f, Airplane a) {
		em.persist(f);
		// em.persist(a); propagated and cascaded from flight automatically
	}

	public void addPilotToFlight(String pilotId, String flightId) {

		TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class);
		// createNamedQuery ..?we're not creating a Query , we're just using the
		// existent one (in Flight.java Model)
		fQuery.setParameter("id", Integer.parseInt(flightId));
		Flight f = fQuery.getSingleResult();// get a flight "f" whose id equals flightId

		TypedQuery<Pilot> pQuery = em.createNamedQuery("Pilot.findById", Pilot.class);
		pQuery.setParameter("id", Integer.parseInt(pilotId));
		Pilot p = pQuery.getSingleResult();

		List<Pilot> pList = f.getPilots();
		pList.add(p);
		f.setPilots(pList);

		p.setFlightForPilot(f);
	}

	public List<Flight> getFlights() {
		// we don't have already a NamedQuery so we use the createQuery is of
		// createNamedQuery Method(Gain Time)
		TypedQuery<Flight> query = em.createQuery("SELECT f FROM Flight f ", Flight.class);
		// each row of data is transalted to an Flight object
		List<Flight> results = query.getResultList();
		return results;
	}

	public void addPassengerToFlight(String passengerId, String flightId) {
		//Equivalent of TypedQuery method
		// by using the following , we avoid SQL query errors in the other method as
		// java can't check SQL querys 
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Passenger> cqPassenger = builder.createQuery(Passenger.class);
		Root<Passenger> pRoot = cqPassenger.from(Passenger.class);
		cqPassenger.select(pRoot).where(builder.equal(pRoot.get("id").as(Integer.class), passengerId));
		Passenger p = em.createQuery(cqPassenger).getSingleResult();

		CriteriaBuilder builder1 = em.getCriteriaBuilder();
		CriteriaQuery<Flight> cqFlight = builder1.createQuery(Flight.class);
		Root<Flight> fRoot = cqFlight.from(Flight.class);
		cqFlight.select(fRoot).where(builder1.equal(fRoot.get("id").as(Integer.class), flightId));
		Flight f = em.createQuery(cqFlight).getSingleResult();

		List<Passenger> pList=f.getPassengers();
		pList.add(p);
		f.setPassengers(pList);
		
		p.getFlights().add(f);
	}
	
	public Flight getFlight(Integer flightId) {
		
		TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class);
		// createNamedQuery ..?we're not creating a Query , we're just using the
		// existent one (in Flight.java Model)
		fQuery.setParameter("id", flightId);
		Flight f=null;
		try {
		f = fQuery.getSingleResult();
		}catch(NoResultException e) {
			return null;
		}
		return f;
		
		
	}

}
