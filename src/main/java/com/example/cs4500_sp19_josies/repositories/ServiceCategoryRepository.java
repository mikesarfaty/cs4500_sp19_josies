package com.example.cs4500_sp19_josies.repositories;

import java.util.List;

import com.example.cs4500_sp19_josies.models.ServiceCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.cs4500_sp19_josies.models.Service;
import com.example.cs4500_sp19_josies.models.ServiceCategory;

public interface ServiceCategoryRepository extends CrudRepository<ServiceCategory, Integer> {
	@Query(value="SELECT serviceCategory FROM ServiceCategory serviceCategory")
	public List<ServiceCategory> findAllServiceCategories();
	@Query(value="SELECT serviceCategory FROM ServiceCategory serviceCategory WHERE serviceCategory.id=:id")
	public ServiceCategory findServiceCategoryById(@Param("id") Integer id);
}