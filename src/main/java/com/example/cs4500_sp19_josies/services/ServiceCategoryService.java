package com.example.cs4500_sp19_josies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.cs4500_sp19_josies.models.ServiceCategory;
import com.example.cs4500_sp19_josies.repositories.ServiceCategoryRepository;

@RestController
@CrossOrigin(origins="*")
public class ServiceCategoryService {

    @Autowired
    ServiceCategoryRepository serviceRepository;

    @GetMapping("/api/categories")
    public List<ServiceCategory> findAllServiceCategories(
            @RequestParam(name="limit", required=false) Integer limit) {
        List<ServiceCategory> categories = serviceRepository.findAllServiceCategories();
        if(limit != null) {
            return categories.subList(0, limit);
        }
        return categories;
    }

    @GetMapping("/api/categories/{serviceCategoryId}")
    public ServiceCategory findServiceCategoryById(
            @PathVariable("serviceCategoryId") Integer id) {
        return serviceRepository.findServiceCategoryById(id);
    }

    @PostMapping("/api/categories")
    public ServiceCategory createServiceCategory(@RequestBody ServiceCategory serviceCategory) {
        return serviceRepository.save(serviceCategory);
    }

    @PutMapping("/api/categories/{serviceCategoryId}")
    public ServiceCategory updateServiceCategory(
            @PathVariable("serviceCategoryId") Integer id,
            @RequestBody ServiceCategory serviceUpdates) {
        ServiceCategory serviceCategory = serviceRepository.findServiceCategoryById(id);
        serviceCategory.setTitle(serviceUpdates.getTitle());
        return serviceRepository.save(serviceCategory);
    }
    
    @DeleteMapping("/api/categories/{serviceCategoryId}")
    public void deleteServiceCategory(
            @PathVariable("serviceCategoryId") Integer id) {
        serviceRepository.deleteById(id);
    }
}
