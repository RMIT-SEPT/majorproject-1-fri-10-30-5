package com.rmit.sept.turtorial.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empID;
    @Size(min=2,max =20, message = "Please enter 2 to 20 characters")
    @NotBlank(message = "Employee first name is required")
    private String firstName;
    @Size(min=2,max =20, message = "Please enter 2 to 20 characters")
    @NotBlank(message = "Employee last name is required")
    private String lastName;
    @Size(min=1,max =3, message = "Please enter 2 to 20 characters")
    @NotBlank(message = "Employee street number is required")
    private int streetNo;
    private String streetName;
    private String state;
    private String number;
    private Date created_At;
    private Date updated_At;

    public Employee() {
    }

    public Long getId() {
        return empID;
    }

    public void setId(Long id) {
        this.empID = empID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }


}
