package com.example.cs4500_sp19_josies.services;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.cs4500_sp19_josies.models.Service;
import com.example.cs4500_sp19_josies.models.ServiceCategory;
import com.example.cs4500_sp19_josies.repositories.ServiceCategoryRepository;
import com.example.cs4500_sp19_josies.repositories.ServiceRepository;

@RestController
@CrossOrigin(origins="*")
public class ServiceCategoryService {

    @Autowired
    ServiceCategoryRepository serviceCategoryRepository;
    
    @Autowired
    ServiceRepository serviceRepository;

    @GetMapping("/api/categories")
    public List<ServiceCategory> findAllServiceCategories(
            @RequestParam(name="limit", required=false) Integer limit) {

        List<ServiceCategory> categories = serviceCategoryRepository.findAllServiceCategories();

        if(limit != null) {
            return categories.subList(0, limit);
        }
        return categories;
    }

    @GetMapping("/api/categories/{serviceCategoryId}")
    public ServiceCategory findServiceCategoryById(
            @PathVariable("serviceCategoryId") Integer id) {
        return serviceCategoryRepository.findServiceCategoryById(id);
    }

    @PostMapping("/api/categories")
    public ServiceCategory createServiceCategory(@RequestBody ServiceCategory serviceCategory) {
        return serviceCategoryRepository.save(serviceCategory);
    }

    @PutMapping("/api/categories/{serviceCategoryId}")
    public ServiceCategory updateServiceCategory(
            @PathVariable("serviceCategoryId") Integer id,
            @RequestBody ServiceCategory serviceUpdates) {
        ServiceCategory serviceCategory = serviceCategoryRepository.findServiceCategoryById(id);
        serviceCategory.setTitle(serviceUpdates.getTitle());
        serviceCategory.setServices(serviceUpdates.getServices());
        return serviceCategoryRepository.save(serviceCategory);
    }
    
    @DeleteMapping("/api/categories/{serviceCategoryId}")
    public void deleteServiceCategory(
            @PathVariable("serviceCategoryId") Integer id) {
    	serviceCategoryRepository.deleteById(id);
    }
}
