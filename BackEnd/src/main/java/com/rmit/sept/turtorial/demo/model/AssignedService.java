package com.rmit.sept.turtorial.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

//This class represents a service that an employee has been assigned by their admin
@Entity
public class AssignedService
{
    //Assigned Service's ID number
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //The Service ID of service being assigned
    private long serviceID;

    //The employee's username of employee being assigned service
    @NotBlank(message = "Employee ID is required")
    private String userName;

    //The date this object was created at
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date created_At;

    //The date this object was last updated at
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date updated_At;

    //No argument constructor for instantiating an Assigned Service object
    public AssignedService(){}

    /*
        3 argument constructor for instantiating an Assigned Service object
        Arguments:
        long id : The ID number for the object
        long serviceID: The Service ID for the service being assigned
        String Username: The username of the employee being assigned the service
     */
    public AssignedService (long id, long serviceID, String userName)
    {
        this.id = id;
        this.serviceID = serviceID;
        this.userName = userName;
    }

    //Getter for ID
    public long getId() { return id; }

    //Setter for ID
    public void setId(long iD) { this.id = iD; }

    //Getter for Service ID
    public long getServiceID() { return serviceID; }

    //Setter for  Service ID
    public void setServiceID(long serviceId) { this.serviceID = serviceId; }

    //Getter for Username
    public String getUserName() { return userName; }

    //Setter for Username
    public void setUserName(String userName) { this.userName = userName; }

    //Getter for created at
    public Date getCreated_At() { return created_At; }

    //Setter for created at
    public void setCreated_At(Date created_At) { this.created_At = created_At; }

    //Getter for updated at
    public Date getUpdated_At() { return updated_At; }

    //Setter for updated at
    public void setUpdated_At(Date updated_At) { this.updated_At = updated_At; }

    //Creates and sets current date when assigned service object is created
    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    //Creates and sets current date when assigned service object is updated
    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }
}