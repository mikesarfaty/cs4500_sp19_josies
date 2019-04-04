package com.example.cs4500_sp19_josies.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
    private String role;
    private String zipCode;

    @OneToMany(mappedBy="provider")
    private List<ServiceAnswer> serviceAnswers;
    @OneToMany(mappedBy = "user")
    private List<FrequentlyAskedAnswer> frequentlyAskedAnswers;
    @ManyToMany(mappedBy = "providers")
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

    public User() {
    }

    public User(String username, String password, String firstName, String lastName,
        String role, String zipCode) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.zipCode = zipCode;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
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

    public Integer findNearest(String given) {
        Integer givenZip = Integer.parseInt(given);
        Integer currZip = Integer.parseInt(this.zipCode);

        Integer diff = Math.abs(givenZip - currZip);

        return diff;
    }
}