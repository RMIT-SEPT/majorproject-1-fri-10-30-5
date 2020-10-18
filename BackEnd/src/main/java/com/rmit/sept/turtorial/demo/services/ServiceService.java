package com.rmit.sept.turtorial.demo.services;

import com.rmit.sept.turtorial.demo.Repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.rmit.sept.turtorial.demo.model.Service;

import java.util.Date;
import java.util.List;

/*
    This class is the service which will be used by the Service Controller
    to retrieve data stored in the database through the use of the Service
    Repository. This class also contains the majority of the business logic.
 */
@org.springframework.stereotype.Service
public class ServiceService
{
    @Autowired
    ServiceRepository serviceRepository;

    //Adds a service
    public Service addService(Service service) { return serviceRepository.save(service); }

    //Get a list of services
    public List<Service> getAllServices() { return serviceRepository.findAll(); }

    //Updates a service
    public Service updateService(Service service)
    {
        Service service1 = serviceRepository.findByServiceId(service.getServiceId());
        if (service1 != null)
        {
            service1.setServiceId(service.getServiceId());
            service1.setName(service.getName());
            service1.setDescription(service.getDescription());
            service1.setDuration(service.getDuration());
            service1.setUpdated_At(new Date());
            return serviceRepository.save(service1);
        }

        return null;
    }
}
