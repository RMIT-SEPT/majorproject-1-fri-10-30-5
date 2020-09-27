package com.rmit.sept.turtorial.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;



@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Username is required")
    private String userName;
    @NotBlank(message = "Password is required")
    @Size(min=4,max =25, message = "Please enter 4 to 25 characters")
    private String password;
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Please enter only alphabetical characters")
    @NotBlank(message = "First name is required")
    private String firstName;
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Please enter only alphabetical characters")
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9 ]*$", message = "Please enter only numerical characters")
    @Size(min=10,max =10, message = "Please enter a 10 digit number")
    private String phone;
    private Boolean emplChecked = false;
    private Boolean adminChecked = false;
    private Boolean custChecked = false;
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date created_At;
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date updated_At;


    public Person() {
    }

    public Person(Long id, @NotBlank(message = "Username is required") String userName, @NotBlank(message = "Password is required")
    @Size(min = 4, max = 25, message = "Please enter 4 to 25 characters") String password, @Pattern(regexp = "^[a-zA-Z ]*$", message = "Please enter only alphabetical characters")
                @NotBlank(message = "First name is required") String firstName, @Pattern(regexp = "^[a-zA-Z ]*$", message = "Please enter only alphabetical characters")
                @NotBlank(message = "Last name is required") String lastName, @NotBlank(message = "Address is required") String address, @NotBlank(message = "Phone is required")
                @Pattern(regexp = "^[0-9 ]*$", message = "Please enter only numerical characters") @Size(min = 10, max = 10, message = "Please enter a 10 digit number") String phone) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getEmplChecked() { return emplChecked; }

    public void setEmplChecked(Boolean workerChecked) { this.emplChecked = workerChecked; }

    public Boolean getAdminChecked() { return adminChecked; }

    public void setAdminChecked(Boolean adminChecked) { this.adminChecked = adminChecked; }

    public Boolean getCustChecked() { return custChecked; }

    public void setCustChecked(Boolean custChecked) { this.custChecked = custChecked; }

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