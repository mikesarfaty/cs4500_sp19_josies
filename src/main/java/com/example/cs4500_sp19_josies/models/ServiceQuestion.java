package com.example.cs4500_sp19_josies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "service_questions")
public class ServiceQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question;
    private QuestionType type;
    private String choices;
    @ManyToOne
    @JsonIgnoreProperties("questions")
    // Added in filtering. Since we added questions to our Service file, we need to add
    // the relationship here as well.
    private Service service;
    @OneToMany(mappedBy = "serviceQuestion")
    private List<ServiceAnswer> serviceAnswers;

    public ServiceQuestion(){}

    public ServiceQuestion(String question, QuestionType type) {
        this.question = question;
        this.type = type;
    }


    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getQuestion() {

        return question;
    }

    public void setQuestion(String question) {

        this.question = question;
    }

    public QuestionType getType() {

        return type;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

    public String getChoices() {
        return choices;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
