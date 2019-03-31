package com.airline.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Flight
 *
 */
@NamedQuery(name = "Flight.findById", query = "SELECT f FROM Flight f WHERE f.id=:id")
@Entity

public class Flight implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	public Flight() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private flightDestinations flightOrigin;

	@Enumerated(EnumType.STRING)
	private flightDestinations flightDestination;

	private Integer price;

	@Temporal(TemporalType.TIMESTAMP) // TIMESTAMP : date & time
	private Date FlightTime;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	// One to One annotation in JPA
	@JoinColumn(name = "airplane_fk") // "fk for foreign key , it's just a name we choose"
	private Airplane airplaneDetail;
	// why not doing the opposite?(airplaine has a flight foregin key)
	// it doesn't make sense ! as the flight logically should have the airplane key

	@OneToMany(mappedBy = "flightForPilot")
	private List<Pilot> pilots;

	@ManyToMany
	@JoinTable(name = "f_p_join", joinColumns = @JoinColumn(name = "flight_fk"), inverseJoinColumns = @JoinColumn(name = "passenger_fk"))
	// creating a table called "f_p_join" having following columns "flight_fk" &&
	// "passenger_fk"
	private List<Passenger> passengers;

	public List<Passenger> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<Passenger> passengers) {
		this.passengers = passengers;
	}

	public Airplane getAirplaneDetail() {
		return airplaneDetail;
	}

	public void setAirplaneDetail(Airplane airplaneDetail) {
		this.airplaneDetail = airplaneDetail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public flightDestinations getFlightOrigin() {
		return flightOrigin;
	}

	public void setFlightOrigin(flightDestinations flightOrigin) {
		this.flightOrigin = flightOrigin;
	}

	public flightDestinations getFlightDestination() {
		return flightDestination;
	}

	public void setFlightDestination(flightDestinations flightDestination) {
		this.flightDestination = flightDestination;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Date getFlightTime() {
		return FlightTime;
	}

	public void setFlightTime(Date flightTime) {
		FlightTime = flightTime;
	}

	public List<Pilot> getPilots() {
		return pilots;
	}

	public void setPilots(List<Pilot> pilots) {
		this.pilots = pilots;
	}

	@Override
	public String toString() {
		return "Flight [id=" + id + ", flightOrigin=" + flightOrigin + ", flightDestination=" + flightDestination
				+ ", price=" + price + ", FlightTime=" + FlightTime + ", airplaneDetail=" + airplaneDetail + ", pilots="
				+ pilots + "]";
	}

}
