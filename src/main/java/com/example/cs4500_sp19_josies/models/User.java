package com.example.cs4500_sp19_josies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String month;
	private String day;
	private String year;
	private String city;
	private String state;
	private String street;
	private String zip;
	

	public User(String username,
			String password,
			String firstName,
			String lastName,
			String role,
			String month,
			String day,
			String year,
			String city,
			String state,
			String street,
			String zip,
			String email,
			List<Service> services) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.month = month;
		this.day = day;
		this.year = year;
		this.city = city;
		this.state = state;
		this.street = street;
		this.zip = zip;
		this.email = email;
		this.services = services;
	}

	@OneToMany(mappedBy="provider")
	@JsonIgnoreProperties("provider")
	private List<ServiceAnswer> serviceAnswers;
	@OneToMany(mappedBy = "user")
	@JsonIgnoreProperties("user")
	private List<FrequentlyAskedAnswer> frequentlyAskedAnswers;
	@ManyToMany(mappedBy = "providers")
	@JsonIgnoreProperties("providers")
	private List<Service> services;

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<FrequentlyAskedAnswer> getFrequentlyAskedAnswers() {
		return frequentlyAskedAnswers;
	}

	public void setFrequentlyAskedAnswers(List<FrequentlyAskedAnswer> frequentlyAskedAnswers) {
		this.frequentlyAskedAnswers = frequentlyAskedAnswers;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ServiceAnswer> getServiceAnswers() {
		return serviceAnswers;
	}

	public void setServiceAnswers(List<ServiceAnswer> serviceAnswers) {
		this.serviceAnswers = serviceAnswers;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}