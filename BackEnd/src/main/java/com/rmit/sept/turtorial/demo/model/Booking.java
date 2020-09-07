package com.rmit.sept.turtorial.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Booking {
    //
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "custID is required")
    @Size(min=1,max =25, message = "Please enter 1 to 25 characters")
    private String custID;
    @NotBlank(message = "empID is required")
    @Size(min=1,max =25, message = "Please enter 1 to 25 characters")
    private String empID;
    @Min(value=0000, message="must be at least 0000")
    @Max(value=2399, message="must be less than 2400")
    private int bookingTime;
   // @Future
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date bookingDate;
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date created_At;
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date updated_At;
    private String bookingStatus;

    public Booking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    public String getCustID() {
        return custID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpID() {
        return empID;
    }

    public int getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(int bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
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

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getBookingStatus() {
        return bookingStatus;
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
