package com.rmit.sept.turtorial.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.*;

import java.util.Date;

/*
    This class represents a person object that will be stored in the system.
    They can be any one of the three user types (admin, customer and employee)
    and in some cases they can be a combination of all 3 user types.
 */
@Entity
public class Person
{
    //The person's ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //The person's username
    @NotBlank(message = "Username is required")
    @Column(unique = true)
    private String userName;

    //The person's password
    @NotBlank(message = "Password is required")
    @Size(min=4,max =25, message = "Please enter 4 to 25 characters")
    private String password;

    //The person's first name
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Please enter only alphabetical characters")
    @NotBlank(message = "First name is required")
    private String firstName;

    //The person's last name
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Please enter only alphabetical characters")
    @NotBlank(message = "Last name is required")
    private String lastName;

    //The person's residential address
    @NotBlank(message = "Address is required")
    private String address;

    //The person's phone number
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = "^[0-9 ]*$", message = "Please enter only numerical characters")
    @Size(min=10,max =10, message = "Please enter a 10 digit number")
    private String phone;

    //Indicates whether the person is an admin, customer or employee
    @NotBlank(message = "Usertype is required")
    @Size(min = 5, max = 8, message = "Please enter either: admin, employee or customer")
    private String userType;

    //The date this object was created at
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date created_At;

    //The date this object was last updated at
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date updated_At;

    //No argument constructor for instantiating a person
    public Person() {
    }


    /*
        7 argument constructor for instantiating a person
        Parameters:
        Long id - The person's ID
        String userName - The person's userName
        String password - The person's password
        String firstName - The person's first name
        String lastName - The person's last name
        String address - The person's residential address
        String phone - The person's phone number
     */
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

    public Person(@NotBlank(message = "Username is required") String userName, @NotBlank(message = "Password is required")
    @Size(min = 4, max = 25, message = "Please enter 4 to 25 characters") String password, @Pattern(regexp = "^[a-zA-Z ]*$", message = "Please enter only alphabetical characters")
                  @NotBlank(message = "First name is required") String firstName, @Pattern(regexp = "^[a-zA-Z ]*$", message = "Please enter only alphabetical characters")
                  @NotBlank(message = "Last name is required") String lastName, @NotBlank(message = "Address is required") String address, @NotBlank(message = "Phone is required")
                  @Pattern(regexp = "^[0-9 ]*$", message = "Please enter only numerical characters") @Size(min = 10, max = 10, message = "Please enter a 10 digit number") String phone) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }

    //Getter for ID
    public Long getId() { return id; }

    //Setter for ID
    public void setId(Long id) { this.id = id; }

    //Getter for userName
    public String getUserName() { return userName; }

    //Setter for userName
    public void setUserName(String userName) { this.userName = userName; }

    //Getter for password
    public String getPassword() { return password; }

    //Setter for password
    public void setPassword(String password) { this.password = password; }

    //Getter for firstName
    public String getFirstName() { return firstName; }

    //Setter for firstName
    public void setFirstName(String firstName) { this.firstName = firstName; }

    //Getter for lastName
    public String getLastName() { return lastName; }

    //Setter for lastName
    public void setLastName(String lastName) { this.lastName = lastName; }

    //Getter for address
    public String getAddress() { return address; }

    //Setter for address
    public void setAddress(String address) { this.address = address; }

    //Getter for phone
    public String getPhone() { return phone; }

    //Setter for phone
    public void setPhone(String phone) { this.phone = phone; }

    //Getter for userType
    public String getUserType() { return userType; }

    //Getter for userType
    public void setUserType(String userType) { this.userType = userType; }

    //Getter for created_At
    public Date getCreated_At() { return created_At; }

    //Setter for created_At
    public void setCreated_At(Date created_At) { this.created_At = created_At; }

    //Getter for created_At
    public Date getUpdated_At() { return updated_At; }

    //Setter for updated_At
    public void setUpdated_At(Date updated_At) { this.updated_At = updated_At; }

    //Creates and sets current date when Person object is created
    @PrePersist
    protected void onCreate() { this.created_At = new Date(); }

    //Creates and sets current date when Person object was last updated
    @PreUpdate
    protected void onUpdate() { this.updated_At = new Date(); }
}