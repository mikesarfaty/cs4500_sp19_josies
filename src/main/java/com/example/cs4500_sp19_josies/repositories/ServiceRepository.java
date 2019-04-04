package com.example.cs4500_sp19_josies.repositories;

import java.util.List;

import com.example.cs4500_sp19_josies.models.Service;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface ServiceRepository extends CrudRepository<Service, Integer> {
    @Query(value="SELECT service FROM Service service")
    public List<Service> findAllServices();
    @Query(value="SELECT service FROM Service service WHERE service.id=:id")
    public Service findServiceById(@Param("id") Integer id);
    @Query(value="SELECT service FROM Service service WHERE service.title=:title")
    public List<Service> findServicesByTitle(@Param("title") String title);
}