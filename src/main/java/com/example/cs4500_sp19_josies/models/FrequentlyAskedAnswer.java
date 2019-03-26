package com.example.cs4500_sp19_josies.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="frequently_asked_answers")
public class FrequentlyAskedAnswer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String answer;
    @ManyToOne
    @JsonIgnore
    private FrequentlyAskedQuestion frequentlyAskedQuestion;
    @ManyToOne
//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
    @JsonIgnore
    private User user;
    @Transient
    private Integer user_id;
    @Transient
    private Integer frequently_asked_question_id;
    @Transient
    private String question;
    public String getQuestion() {
        return frequentlyAskedQuestion.getQuestion();
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public FrequentlyAskedQuestion getFrequentlyAskedQuestion() {
        return frequentlyAskedQuestion;
    }
    public void setFrequentlyAskedQuestion(FrequentlyAskedQuestion frequentlyAskedQuestion) {
        this.frequentlyAskedQuestion = frequentlyAskedQuestion;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }
    public Integer getUser_id() {
        return this.user.getId();
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public Integer getFrequently_asked_question_id() {
        return this.frequentlyAskedQuestion.getId();
    }
    public void setFrequently_asked_question_id(Integer frequently_asked_question_id) {
        this.frequently_asked_question_id = frequently_asked_question_id;
    }

}