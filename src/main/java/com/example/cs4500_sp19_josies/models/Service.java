package com.example.cs4500_sp19_josies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="services")
public class Service {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String title;
    @OneToMany(mappedBy="service")
    // List of questions for the given service, needed for service provider navigator.
    private List<ServiceQuestion> questions;
    @ManyToMany
    @JsonIgnoreProperties("services")
    @JoinTable(
            name="PROVIDERS_SERVICES",
            joinColumns=@JoinColumn(name="SERVICE_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="USER_ID", referencedColumnName="ID"))
    private List<User> providers;
    @ManyToMany(mappedBy="services")
    @JsonIgnoreProperties("services")
    private List<ServiceCategory> serviceCategories;
    public List<ServiceCategory> getServiceCategories() {
        return serviceCategories;
    }
    public void setServiceCategories(List<ServiceCategory> serviceCategories) {
        this.serviceCategories = serviceCategories;
    }
    public List<User> getProviders() {
        return providers;
    }
    public void setProviders(List<User> providers) {
        this.providers = providers;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<ServiceQuestion> getQuestions() {
        return questions;
    }
    public void setQuestions(List<ServiceQuestion> questions) {
        this.questions = questions;
    }
    public Service(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
    public Service() {}

}