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
    private Boolean trueFalseAnswer;
    private Integer maxRangeAnswer;
    private Integer minRangeAnswer;
    private Integer choiceAnswer;
    @ManyToOne
    @JsonIgnore
    private ServiceQuestion serviceQuestion;
    @ManyToOne
    @JsonIgnore
    private User provider;

    public ServiceAnswer(Boolean trueFalseAnswer, Integer maxRangeAnswer, Integer minRangeAnswer, Integer choiceAnswer, ServiceQuestion serviceQuestion, User provider) {
        this.trueFalseAnswer = trueFalseAnswer;
        this.maxRangeAnswer = maxRangeAnswer;
        this.minRangeAnswer = minRangeAnswer;
        this.choiceAnswer = choiceAnswer;
        this.serviceQuestion = serviceQuestion;
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

    public Boolean getTrueFalseAnswer() {
        return trueFalseAnswer;
    }

    public void setTrueFalseAnswer(Boolean trueFalseAnswer) {
        this.trueFalseAnswer = trueFalseAnswer;
    }

    public Integer getMaxRangeAnswer() {
        return maxRangeAnswer;
    }

    public void setMaxRangeAnswer(Integer maxRangeAnswer) {
        this.maxRangeAnswer = maxRangeAnswer;
    }

    public Integer getMinRangeAnswer() {
        return minRangeAnswer;
    }

    public void setMinRangeAnswer(Integer minRangeAnswer) {
        this.minRangeAnswer = minRangeAnswer;
    }

    public Integer getChoiceAnswer() {
        return choiceAnswer;
    }

    public void setChoiceAnswer(Integer choiceAnswer) {
        this.choiceAnswer = choiceAnswer;
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