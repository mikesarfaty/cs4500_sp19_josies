package com.example.cs4500_sp19_josies.services;

import java.util.ArrayList;
import java.util.List;

import com.example.cs4500_sp19_josies.models.Service;
import com.example.cs4500_sp19_josies.models.User;
import com.example.cs4500_sp19_josies.repositories.ServiceRepository;
import com.example.cs4500_sp19_josies.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@CrossOrigin(origins="*")
public class ServiceService {
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/api/services")
    public List<Service> findAllService() {
        return serviceRepository.findAllServices();
    }
    @GetMapping("/api/services/{serviceId}")
    public Service findServiceById(
            @PathVariable("serviceId") Integer id) {
        return serviceRepository.findServiceById(id);
    }
    @PostMapping("/api/services")
    public Service createService(@RequestBody Service service) {
        return serviceRepository.save(service);
    }
    @PutMapping("/api/services/{serviceId}")
    public Service updateService(
            @PathVariable("serviceId") Integer id,
            @RequestBody Service serviceUpdates) {
        Service service = serviceRepository.findServiceById(id);
        service.setTitle(serviceUpdates.getTitle());
        ArrayList<User> providers = new ArrayList<User>();
        for (User u : serviceUpdates.getProviders()) {
        	User uu = userRepository.findUserById(u.getId());
        	providers.add(uu);
        }
        service.setProviders(providers);
        return serviceRepository.save(service);
    }
    @DeleteMapping("/api/services/{serviceId}")
    public void deleteService(
            @PathVariable("serviceId") Integer id) {
        serviceRepository.deleteById(id);
    }
    
    @PostMapping("/api/services/{serviceId}/users/{userId}")
    public Service registerProvider(
    		@PathVariable("serviceId") Integer serviceId,
    		@PathVariable("userId") Integer userId) {
    	User user = userRepository.findUserById(userId);
    	Service service = serviceRepository.findServiceById(serviceId);
    	List<User> providers = service.getProviders();
    	providers.add(user);
    	service.setProviders(providers);
    	return serviceRepository.save(service);
    }
}
