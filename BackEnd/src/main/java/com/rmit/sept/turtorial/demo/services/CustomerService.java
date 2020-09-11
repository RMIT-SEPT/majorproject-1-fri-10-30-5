package com.rmit.sept.turtorial.demo.services;


import com.rmit.sept.turtorial.demo.Repositories.CustomerRepository;
import com.rmit.sept.turtorial.demo.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    //post services
    public String addCustomer(Customer customer) {
        String message;
        Customer customer1 = customerRepository.findById(customer.getUserName()).orElse(null);
        if (customer1 == null){
            customerRepository.save(customer);
            message = customer.getUserName() + " added successfully";
        }else{
            message = customer.getUserName() + " already exists";
        }
        return message;
    }

    //get services
    public Customer getCustomerByUserName(String userName) {

        //logic
        return customerRepository.findById(userName).orElse(null);
    }

    //put services
    public Customer updateCustomer(Customer customer) {
        Customer customer1 = customerRepository.findById(customer.getUserName()).orElse(null);
        if (customer1 != null){
            customer1.setFirstName(customer.getFirstName());
            customer1.setLastName(customer.getLastName());
            customer1.setPassword(customer.getPassword());
            customer1.setPhone(customer.getPhone());
            customer1.setAddress(customer.getAddress());
            customer1.setUpdated_At(new Date());
        }
        return customerRepository.save(customer1);
    }

    //delete services
    public String deleteCustomer(String userName){
        customerRepository.deleteById(userName);
        return "Customer " + userName + " has been successfully removed";
    }
}
