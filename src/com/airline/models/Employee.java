package com.airline.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Employee
 *
 */
@Entity
public class Employee implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequencee")
	@SequenceGenerator(name = "id_Sequencee", sequenceName = "ID_SEQE")
	private Integer id;
	
	@Column(name="first_name")
	private String fName;
	
	@Column(name="last_name")
	private String lName;
	
	
	// without this annotation the gender will be
	// saved as a digit (Female saved as 0 and male as 1)
	@Column(name="gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Temporal(TemporalType.DATE)
	// this annotation'sspecific for a date column
	@Column(name="empDob")
	private Date empDob;
	
	@Column(name="phone")
	private String phone;
	
	@Column(name="email")
	private String email;
	
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="dep")
	private Department dep;
	
	
	
	public Employee(String fName, String lName, Gender gender, Date empDob, String phone, String email,
			Department dep) {
		this.fName = fName;
		this.lName = lName;
		this.gender = gender;
		this.empDob = empDob;
		this.phone = phone;
		this.email = email;
		this.dep = dep;
	}

	public Employee() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Department getDep() {
		return dep;
	}

	public void setDep(Department dep) {
		this.dep = dep;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getEmpDob() {
		return empDob;
	}

	public void setEmpDob(Date empDob) {
		this.empDob = empDob;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", fName=" + fName + ", lName=" + lName + ", gender=" + gender + ", empDob="
				+ empDob + ", phone=" + phone + ", email=" + email + "]";
	}

	
	
	
   
}
