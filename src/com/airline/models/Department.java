package com.airline.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Departement
 *
 */

@Entity
@NamedQuery(name = "Department.findById", query = "SELECT d FROM Department d WHERE d.id=:id")
@Table(name="department")
public class Department implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	//@Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
	private Integer id;
	
	//@Column(name="depname")
	private String name;
	
	@OneToMany(mappedBy="dep",cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Employee>employees;
	
	public Department() {
		
	}
	
	public Department( String name) {
		this.name=name;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", employees=" + employees + "]";
	}
	
	
   
}
