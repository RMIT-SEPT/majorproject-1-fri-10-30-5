package com.rmit.sept.turtorial.demo.services;

import com.rmit.sept.turtorial.demo.Repositories.ServiceRepository;
import com.rmit.sept.turtorial.demo.model.AssignedService;
import org.springframework.beans.factory.annotation.Autowired;
import com.rmit.sept.turtorial.demo.model.Service;

import java.util.Date;
import java.util.List;


@org.springframework.stereotype.Service
public class ServiceService
{
    @Autowired
    ServiceRepository serviceRepository;

    //Post service
    public Service addService(Service service) { return serviceRepository.save(service); }

    //Get list of services
    public List<Service> getService() { return serviceRepository.findAll(); }

    //Update a service
    public Service updateService(Service service)
    {
        Service service1 = serviceRepository.findByiD(service.getiD());
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

//    public String deleteService(long iD)
//    {
//        empServiceRepository.deleteById(iD);
//        return "Service " + iD + " has been successfully removed";
//    }
}
