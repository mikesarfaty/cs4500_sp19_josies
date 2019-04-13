package com.example.cs4500_sp19_josies.services;

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
        return serviceCategoryRepository.save(serviceCategory);
    }
    
    @PostMapping("/api/categories/{serviceCategoryId}/services/{serviceId}")
    public ServiceCategory registerServiceToCategory(
    		@PathVariable("serviceCategoryId") Integer categoryId,
    		@PathVariable("serviceId") Integer serviceId) {
    	ServiceCategory sc = serviceCategoryRepository.findServiceCategoryById(categoryId);
    	Service s = serviceRepository.findServiceById(serviceId);
    	List<Service> services = sc.getServices();
    	for (Service serviceInCategory : services) {
    		if (serviceInCategory.getId() == s.getId()) {
    			return sc;
    		}
    	}
    	services.add(s);
    	sc.setServices(services);
    	return serviceCategoryRepository.save(sc);
    }
    
    @DeleteMapping("/api/categories/{serviceCategoryId}/services/{serviceId}")
    public ServiceCategory deregisterServiceToCategory(
    		@PathVariable("serviceCategoryId") Integer categoryId,
    		@PathVariable("serviceId") Integer badServiceId) {
    	ServiceCategory sc = serviceCategoryRepository.findServiceCategoryById(categoryId);
    	Service badService = serviceRepository.findServiceById(badServiceId);
    	List<Service> services = sc.getServices();
    	List<Service> newServices = new ArrayList<Service>();
    	for (Service serviceInCategory : services) {
    		if (serviceInCategory.getId() != badService.getId()) {
    			newServices.add(serviceInCategory);
    		}
    	}
    	sc.setServices(newServices);
    	return serviceCategoryRepository.save(sc);
    }
    
    @DeleteMapping("/api/categories/{serviceCategoryId}")
    public void deleteServiceCategory(
            @PathVariable("serviceCategoryId") Integer id) {
    	serviceCategoryRepository.deleteById(id);
    }
}
