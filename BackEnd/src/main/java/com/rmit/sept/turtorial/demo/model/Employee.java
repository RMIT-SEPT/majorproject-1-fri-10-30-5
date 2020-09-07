package com.rmit.sept.turtorial.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class Employee {
    @Id
    @NotBlank(message = "Username is required")
    private String userName;
    @NotBlank(message = "Password is required")
    @Size(min=4,max =25, message = "Please enter 4 to 25 characters")
    private String password;
    @Size(min=2,max =20, message = "Please enter 2 to 20 characters")
    @NotBlank(message = "Admin is required")
    private String admin;
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Please enter only alphabetical characters")
    @NotBlank(message = "Employee name is required")
    private String name;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9]*$", message = "Please enter only numerical characters")
    @Size(min=10,max =10, message = "Please enter a 10 digit number")
    private String phone;
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date created_At;
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date updated_At;
    public Employee() {
    }

    public String getUserName() { return userName; }

    public void setUserName(String username) {this.userName = username; }

    public String getAdmin() { return admin; }

    public void setAdmin(String admin) { this.admin = admin; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

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
