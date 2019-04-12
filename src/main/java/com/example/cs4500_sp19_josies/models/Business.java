package com.example.cs4500_sp19_josies.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

public class Business {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String business_name;
    private Integer year_founded;
    private Integer number_of_employees;
    private String email;

    private String street;
    private String city;
    private String state;
    private Integer zipcode;

    private List<String>payment;

    private String facebook_url;
    private String instagram_url;
    private String twitter_url;

    private User business_owner;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public Integer getYear_founded() {
        return year_founded;
    }

    public void setYear_founded(Integer year_founded) {
        this.year_founded = year_founded;
    }

    public Integer getNumber_of_employees() {
        return number_of_employees;
    }

    public void setNumber_of_employees(Integer number_of_employees) {
        this.number_of_employees = number_of_employees;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public List<String> getPayment() {
        return payment;
    }

    public void setPayment(List<String> payment) {
        this.payment = payment;
    }

    public String getFacebook_url() {
        return facebook_url;
    }

    public void setFacebook_url(String facebook_url) {
        this.facebook_url = facebook_url;
    }

    public String getInstagram_url() {
        return instagram_url;
    }

    public void setInstagram_url(String instagram_url) {
        this.instagram_url = instagram_url;
    }

    public String getTwitter_url() {
        return twitter_url;
    }

    public void setTwitter_url(String twitter_url) {
        this.twitter_url = twitter_url;
    }

    public User getBusiness_owner() {
        return business_owner;
    }

    public void setBusiness_owner(User owner) {
        this.business_owner = owner;
    }


}
