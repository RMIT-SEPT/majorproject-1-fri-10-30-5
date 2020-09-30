package com.rmit.sept.turtorial.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class AssignedService
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long iD;
    @Min(value = 1, message = "Enter at least one character")
    private long serviceId;
    @NotBlank(message = "Employee ID is required")
    private String userName;
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date created_At;
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date updated_At;

    public long getiD() { return iD; }

    public void setiD(long iD) { this.iD = iD; }

    public long getServiceId() { return serviceId; }

    public void setServiceId(long serviceId) { this.serviceId = serviceId; }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }
}
