package com.example.cs4500_sp19_josies.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @OneToMany(mappedBy="provider")
    private List<ServiceAnswer> serviceAnswers;
    @OneToMany(mappedBy="user")
    private List<FrequentlyAskedAnswer> frequentlyAskedAnswers;
    @ManyToMany(mappedBy="providers")
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
    public User() {}
    public User(Integer id, String username, String password, String firstName, String lastName) {

    private String role;

    @OneToMany(mappedBy="provider")
    private List<ServiceAnswer> serviceAnswers;
    @OneToMany(mappedBy = "user")
    private List<FrequentlyAskedAnswer> frequentlyAskedAnswers;
    @ManyToMany(mappedBy = "providers")
    private List<Service> services;
    }

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
}