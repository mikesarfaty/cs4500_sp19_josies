package com.example.cs4500_sp19_josies.repositories;

import com.example.cs4500_sp19_josies.models.ServiceCategory;
import org.springframework.data.repository.*;

public interface ServiceCategoryRepository
  extends CrudRepository<ServiceCategory, Integer> {}
