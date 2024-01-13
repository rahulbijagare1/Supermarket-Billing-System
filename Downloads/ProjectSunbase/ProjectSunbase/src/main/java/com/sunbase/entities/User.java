package com.sunbase.entities;





import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name="USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String email;
	private String password;
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Customer> customers=new ArrayList<>();
	public User(int id, String email, String password, List<Customer> customers) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.customers = customers;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", password=" + password + ", customers=" + customers + "]";
	}
	public String getRole() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
