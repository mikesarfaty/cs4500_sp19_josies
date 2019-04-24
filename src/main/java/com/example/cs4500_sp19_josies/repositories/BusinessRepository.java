package com.example.cs4500_sp19_josies.repositories;

import com.example.cs4500_sp19_josies.models.Business;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessRepository extends CrudRepository<Business, Integer> {
  @Query(value="SELECT business FROM Business business")
  public List<Business> findAllBusinesses();
  @Query(value="SELECT business FROM Business business WHERE business.id=:id")
  public Business findBusinessById(@Param("id") Integer id);
}
