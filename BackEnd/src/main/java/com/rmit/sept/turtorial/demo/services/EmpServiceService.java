package com.rmit.sept.turtorial.demo.services;

import com.rmit.sept.turtorial.demo.Repositories.EmpServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rmit.sept.turtorial.demo.model.EmpService;

import java.util.Date;
import java.util.List;


@Service
public class EmpServiceService
{
    @Autowired
    EmpServiceRepository empServiceRepository;

    //Post service
    public EmpService addService(EmpService service) { return empServiceRepository.save(service); }

    //Get list of services
    public List<EmpService> getService() { return empServiceRepository.findAll(); }

    //Update a service
    public EmpService updateService(EmpService service)
    {
        EmpService service1 = empServiceRepository.findByiD(service.getiD());
        if (service1 != null)
        {
            service1.setServiceId(service.getServiceId());
            service1.setName(service.getName());
            service1.setDescription(service.getDescription());
            service1.setDuration(service.getDuration());
            service1.setUpdated_At(new Date());
            return empServiceRepository.save(service1);
        }

        return null;
    }

//    public String deleteService(long iD)
//    {
//        empServiceRepository.deleteById(iD);
//        return "Service " + iD + " has been successfully removed";
//    }
}
