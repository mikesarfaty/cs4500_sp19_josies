package com.example.cs4500_sp19_josies.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.cs4500_sp19_josies.models.Service;
import com.example.cs4500_sp19_josies.repositories.ServiceRepository;
import com.example.cs4500_sp19_josies.repositories.UserRepository;

@org.springframework.stereotype.Service
public class DefaultServiceService implements ServiceService {
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    UserRepository userRepository;
    public List<Service> findAllServices() {
        return serviceRepository.findAllServices();
    }
    public Service findServiceById(Integer id) {
        return serviceRepository.findServiceById(id);
    }
    public List<Service> findAllServicesForUser(Integer userId) {
        return userRepository.findUserById(userId).getServices();
    }}
