package com.example.cs4500_sp19_josies.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.cs4500_sp19_josies.models.Service;
import com.example.cs4500_sp19_josies.services.ServiceService;
import com.example.cs4500_sp19_josies.services.UserService;

@RestController
@CrossOrigin(origins="*")
public class ServiceController {
    @Autowired
    ServiceService serviceService;
    @Autowired
    UserService userService;
    @GetMapping("/api/services")
    public List<Service> findAllServices() {
        return serviceService.findAllServices();
    }
    @GetMapping("/api/services/{serviceId}")
    public Service findServiceById(
            @PathVariable("serviceId") Integer id) {
        return serviceService.findServiceById(id);
    }
    @GetMapping("/api/users/{userId}/services")
    public List<Service> findAllServicesForUser(
            @PathVariable("userId") Integer userId) {
        return userService.findUserById(userId).getServices();
    }
}