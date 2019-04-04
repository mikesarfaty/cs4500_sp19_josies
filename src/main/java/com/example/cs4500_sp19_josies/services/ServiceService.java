package com.example.cs4500_sp19_josies.services;

import com.example.cs4500_sp19_josies.models.User;
import com.example.cs4500_sp19_josies.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.example.cs4500_sp19_josies.models.Service;
import com.example.cs4500_sp19_josies.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@CrossOrigin(origins = "*")
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

  @GetMapping("/api/services/search")
  public List<User> findServiceProviders(@RequestParam("searchTerm") String searchTerm,
      @RequestParam("zip") String zipCode) {

    List<User> providers = new ArrayList<>();

    List<Service> services = serviceRepository.findServicesByTitle(searchTerm);

    if (services.isEmpty()) {
      User provider = userRepository.findByUsername(searchTerm);
      if (provider != null) {
        providers.add(provider);
      }
      return providers;
    }

    for (Service service : services) {
      for (User provider : service.getProviders()) {
        if (!providers.contains(provider)) {
          providers.add(provider);
        }
      }
    }

//    Collections
//        .sort(providers, Comparator.comparing((User provider) -> provider.findNearest(zipCode)));

    return providers;

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
    return serviceRepository.save(service);
  }

  @DeleteMapping("/api/services/{serviceId}")
  public void deleteService(
      @PathVariable("serviceId") Integer id) {
    serviceRepository.deleteById(id);
  }
}
