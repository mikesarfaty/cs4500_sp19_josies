package com.example.cs4500_sp19_josies.services;

import com.example.cs4500_sp19_josies.models.Business;
import com.example.cs4500_sp19_josies.repositories.BusinessRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class BusinessService {
  @Autowired
  BusinessRepository repository;
  @GetMapping("/api/businesses")
  public List<Business> findAllBusinesses() {
    return repository.findAllBusinesses();
  }
  @GetMapping("/api/businesses/{id}")
  public Business findBusinessById(
          @PathVariable("id") Integer id) {
    return repository.findBusinessById(id);
  }

  @PostMapping("/api/businesses")
  public Business createBusiness(@RequestBody Business business) {
    return repository.save(business);
  }

  @PutMapping("/api/businesses/{id}")
  public Business updateBusiness(
          @PathVariable("id") Integer id,
          @RequestBody Business updates) {
    Business b = repository.findBusinessById(id);
    b.setBusiness_name(updates.getBusiness_name());
    b.setYear_founded(updates.getYear_founded());
    b.setNumber_of_employees(updates.getNumber_of_employees());
    b.setEmail(updates.getEmail());
    b.setStreet(updates.getStreet());
    b.setCity(updates.getCity());
    b.setState(updates.getState());
    b.setZipcode(updates.getZipcode());
    b.setPayments(updates.getPayments());
    b.setFacebook_url(updates.getFacebook_url());
    b.setInstagram_url(updates.getInstagram_url());
    b.setTwitter_url(updates.getTwitter_url());
    return repository.save(b);
  }

  @DeleteMapping("/api/businesses/{id}")
  public void deleteFrequentlyAskedQuestion(
          @PathVariable("id") Integer id) {
    Business b = repository.findBusinessById(id);
    repository.deleteById(id);
  }
}
