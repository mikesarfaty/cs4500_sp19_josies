package com.example.cs4500_sp19_josies.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="service_categories")
public class ServiceCategory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private Integer popularity;
    private String icon;

    @ManyToMany
//    @JsonIgnore
    @JoinTable(
            name="CATEGORIES_SERVICES",
            joinColumns=@JoinColumn(name="CATEGORY_ID", referencedColumnName="ID"),
            inverseJoinColumns=@JoinColumn(name="SERVICE_ID", referencedColumnName="ID"))
    private List<Service> services;
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
    public List<Service> getServices() {
        return services;
    }
    public void setServices(List<Service> services) {
        this.services = services;
    }

    public ServiceCategory() {}
    public ServiceCategory(Integer id, String title) {
        this.id = id;
        this.title = title;
    }
    public Integer getPopularity() {
        return popularity;
    }
    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
}