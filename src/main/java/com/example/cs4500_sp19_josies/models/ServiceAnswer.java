package com.example.cs4500_sp19_josies.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "service_answers")
public class ServiceAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // Updated for filtering. Instead of multiple types of answer, just have an answer title.
    private String title;
    @ManyToOne
    @JsonIgnore
    private ServiceQuestion serviceQuestion;
    @ManyToOne
    @JsonIgnore
    private User provider;

    public ServiceAnswer(ServiceQuestion serviceQuestion, String title, User provider) {
        this.serviceQuestion = serviceQuestion;
        this.title = title;
        this.provider = provider;
    }

    public ServiceAnswer() {

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

    public ServiceQuestion getServiceQuestion() {
        return serviceQuestion;
    }

    public void setServiceQuestion(ServiceQuestion serviceQuestion) {
        this.serviceQuestion = serviceQuestion;
    }

    public User getProvider() {
        return provider;
    }

    public void setProvider(User provider) {
        this.provider = provider;
    }
}