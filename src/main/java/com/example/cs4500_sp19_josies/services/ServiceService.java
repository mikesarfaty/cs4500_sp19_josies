package com.example.cs4500_sp19_josies.services;

import java.util.List;

import com.example.cs4500_sp19_josies.models.Service;

public interface ServiceService {
    public List<Service> findAllServices();
    public Service findServiceById(Integer id);
    public List<Service> findAllServicesForUser(Integer userId);
}