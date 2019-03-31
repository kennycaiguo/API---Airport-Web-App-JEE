package com.airline.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Flight;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class PilotService
 */
@Stateless
@LocalBean
public class PilotService {
	
	

	@PersistenceContext(unitName="airline")
	EntityManager em;
	
    /**
     * Default constructor. 
     */
    public PilotService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addPilot(Pilot p) {
    	
    	em.persist(p);
    }
    
    public void addNewPilotToFlight(Pilot p, String flightId) {

    	em.persist(p);
    	
		TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class);
		//createNamedQuery ..?we're not creating a Query , we're just using the existent one (in Flight.java Model)
		fQuery.setParameter("id", Integer.parseInt(flightId));
		Flight f = fQuery.getSingleResult();// get a flight "f" whose id equals flightId
		
		List<Pilot> pList = f.getPilots();
		pList.add(p);
		f.setPilots(pList);
		
		p.setFlightForPilot(f);
	}
	

}
