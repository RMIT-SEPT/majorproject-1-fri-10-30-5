package com.rmit.sept.turtorial.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

/*
    This class represents a Service object that the business offers
    to customers and can be performed by employees of the organisation.
 */
@Entity
public class Service
{

    //Service's ID number
    @Id
    private long serviceId;

    //Service's name
    @NotBlank(message = "Name is required")
    private String name;

    //Service's description
    @NotBlank(message = "Description is required")
    private String description;

    //Service's duration
    @Min(value= 0, message="must be at least 0000")
    @Max(value=2359, message="must be 2359 or less")
    private int duration;

    //The date this object was created
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date created_At;

    //The date this object was created
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date updated_At;

    /*
        No argument constructor for instantiating Service object
     */
    public Service(){}

    /*
        Four Argument Constructor for Service
        Arguments:
        long serviceId: ID number for the Service
        String name: Name of the Service
        String description: Description of the Service
        int duration: The length of time the Service takes to perform
     */
    public Service(long serviceId, String name, String description, int duration)
    {
        this.serviceId = serviceId;
        this.name = name;
        this.description = description;
        this.duration = duration;
    }

    //Getter for serviceId
    public Long getServiceId() { return serviceId; }

    //Setter for serviceId
    public void setServiceId(Long serviceId) { this.serviceId = serviceId; }

    //Getter for name
    public String getName() { return name; }

    //Setter for name
    public void setName(String name) { this.name = name; }

    //Getter for description
    public String getDescription() { return description; }

    //Setter for description
    public void setDescription(String description) { this.description = description; }

    //Getter for duration
    public int getDuration() { return duration; }

    //Setter for duration
    public void setDuration(int time) { this.duration = time; }

    //Getter for created_At
    public Date getCreated_At() { return created_At; }

    //Setter for created_At
    public void setCreated_At(Date created_At) { this.created_At = created_At; }

    //Getter for updated_At
    public Date getUpdated_At() { return updated_At; }

    //Setter for updated_At
    public void setUpdated_At(Date updated_At) { this.updated_At = updated_At; }

    //Creates and sets the current date when Service object is created
    @PrePersist
    protected void onCreate() { this.created_At = new Date(); }

    //Creates and sets the current date when Service object is created
    @PreUpdate
    protected void onUpdate() { this.updated_At = new Date(); }
}