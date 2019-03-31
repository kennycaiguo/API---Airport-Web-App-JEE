package com.airline.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Passenger
 *
 */
@Entity
@XmlRootElement // Allow Passenger object to be converted to XML(through JAX-RS)
public class Passenger implements Serializable {

	@Transient // this property won't respresent a colomn in the table
	private static final long serialVersionUID = 1L;

	public Passenger() {
		super();
	}

	@Id // primary key annotation
	@GeneratedValue(strategy = GenerationType.AUTO) // Id auto generation
	private Integer id;

	private String firstName;

	private String lastName;

	@Temporal(TemporalType.DATE)
	// this annotation'sspecific for a date column
	private Date dob;

	@Enumerated(EnumType.STRING)
	// without this annotation the gender will be
	// saved as a digit (Female saved as 0 and male as 1)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private flightClass flightClass;
	
	@ManyToMany(mappedBy="passengers")
	private List<Flight> flights; 
	//the flight tickets the passengers has

	
	public Integer getId() {
		return id;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public flightClass getFlightClass() {
		return flightClass;
	}

	public void setFlightClass(flightClass flightClass) {
		this.flightClass = flightClass;
	}

	@Override
	public String toString() {
		return "Passenger [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob
				+ ", gender=" + gender + ", flightClass=" + flightClass + "]";
	}

}
