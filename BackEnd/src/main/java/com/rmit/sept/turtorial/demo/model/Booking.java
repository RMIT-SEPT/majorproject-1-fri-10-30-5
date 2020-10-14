package com.rmit.sept.turtorial.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

/*
    This class represents a booking object that is created when a customer or
    admin makes a valid booking.
 */
@Entity
public class Booking
{
    //Booking's ID number
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Customer ID of customer that is booked
    @NotBlank(message = "custID is required")
    @Size(min=1,max =25, message = "Please enter 1 to 25 characters")
    private String custID;

    //Employee ID of employee that is booked
    @NotBlank(message = "empID is required")
    @Size(min=1,max =25, message = "Please enter 1 to 25 characters")
    private String empID;

    //The Booking start time
    @Min(value=0, message="must be at least 0000")
    @Max(value=2359, message="must be less than 2400")
    private int bookingTime;

    //The date the booking has been made for
    @JsonFormat(pattern ="yyyy-MM-dd")
    private String bookingDate;

    //The date this object was created at
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date created_At;

    //The date this object was last updated at
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date updated_At;

    //The status of the booking to be updated by admin
    private String bookingStatus;

    //No argument constructor for instantiating booking object
    public Booking() {}

    /*
        6 Argument constructor for instantiating booking object
        Parameters:
        long id - ID number of booking
        String custID - Customer ID of customer that is being booked in
        String empID - Employee ID of employee that is being booked in
        int time - Time that the booking is being made
        String date - Date that the booking is being made
        String status - Current status of the booking
     */
    public Booking(long id, String custID, String empID, int time, String date, String status) {
        this.id = id;
        this.custID = custID;
        this.empID = empID;
        this.bookingTime = time;
        this.bookingDate = date;
        this.bookingStatus= status;
    }

    /*
        4 Argument constructor for instantiating booking object
        Parameters:
        String custID - Customer ID of customer that is being booked in
        String empID - Employee ID of employee that is being booked in
        int time - Time that the booking is being made
        String date - Date that the booking is being made
    */
    public Booking(String custID, String empID, int time, String date) {
        this.custID = custID;
        this.empID = empID;
        this.bookingTime = time;
        this.bookingDate = date;
    }

    //Getter for ID
    public Long getId() { return id; }

    //Setter for ID
    public void setId(Long id) { this.id = id; }

    ////Getter for custID
    public void setCustID(String custID) { this.custID = custID; }

    //Setter for custID
    public String getCustID() { return custID; }

    //Getter for empID
    public void setEmpID(String empID) { this.empID = empID; }

    //Setter for empID
    public String getEmpID() { return empID; }

    //Getter for bookingTime
    public int getBookingTime() { return bookingTime; }

    //Setter for bookingTime
    public void setBookingTime(int bookingTime) { this.bookingTime = bookingTime; }

    //Getter for bookingDate
    public String getBookingDate() { return bookingDate; }

    //Setter for bookingDate
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    //Getter for created_At
    public Date getCreated_At() { return created_At; }

    //Setter for created_At
    public void setCreated_At(Date created_At) { this.created_At = created_At; }

    //Getter for updated_At
    public Date getUpdated_At() { return updated_At; }

    //Setter for updated_At
    public void setUpdated_At(Date updated_At) { this.updated_At = updated_At; }

    //Getter for bookingStatus
    public String getBookingStatus() { return bookingStatus; }

    //Setter for bookingStatus
    public void setBookingStatus(String bookingStatus) { this.bookingStatus = bookingStatus; }

    //Creates and sets current date object when booking object is created
    @PrePersist
    protected void onCreate() { this.created_At = new Date(); }

    //Creates and sets current date object when booking object is updated
    @PreUpdate
    protected void onUpdate() { this.updated_At = new Date(); }

}
