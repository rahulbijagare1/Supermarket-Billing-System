package com.sunbase.entities;





import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@Table(name="CUSTOMER")
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cId;
	private String firstname;
	private String lastname;
	private String street;
	private String address;
	private String city;
	private String state;
	private String email;
	private String phone;
	
	@ManyToOne	
	@JsonIgnore
	private User user;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Customer(int cId, String firstname, String lastname, String street, String address, String city,
			String state, String email, String phone, User user) {
		super();
		this.cId = cId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.street = street;
		this.address = address;
		this.city = city;
		this.state = state;
		this.email = email;
		this.phone = phone;
		this.user = user;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return this.cId==((Customer)obj).getcId();

}
}

