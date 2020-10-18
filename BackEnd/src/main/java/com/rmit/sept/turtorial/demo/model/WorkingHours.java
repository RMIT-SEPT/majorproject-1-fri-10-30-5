package com.rmit.sept.turtorial.demo.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonFormat;

/*
    This class represents the Working Hours that an employee has been assigned for
    a specified day.
 */
@Entity
public class WorkingHours
{
    //The shift's shift number
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //The employee ID of employee being assigned this shift
    @NotBlank(message = "empID is required")
    @Size(min=1,max =25, message = "Please enter 1 to 25 characters")
    private String empID;

    //The shift's start time
    @Min(value= 0, message="must be at least 0000")
    @Max(value=2359, message="must be 2359 or less")
    private int startTime;

    //The shift's end time
    @Min(value= 0, message="must be at least 0000")
    @Max(value=2359, message="must be 2359 or less")
    private int endTime;

    //The date this shift is being allocated for
    @JsonFormat(pattern ="yyyy-MM-dd")
    private String workDate;

    //Determines whether or not this time is available
    private boolean available = true;

    //The date this object was created
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date created_At;

    //The date this object was created
    @JsonFormat(pattern ="yyyy-MM-dd")
    private Date updated_At;

    //No argument constructor for instantiating a Working Hours object
    public WorkingHours(){}

    /*
        5 argument constructor for instantiating a Working Hours object
        Arguments:
        Long id - the Working Hours ID number
        String empID - the employee that will be assigned this shift
        int startTime - the start time for this shift
        int endTime - the end time for this shift
        String workDate - the date that this shift is being allocated for
     */
    public WorkingHours(Long id, @NotBlank(message = "empID is required")
    @Size(min = 1, max = 25, message = "Please enter 1 to 25 characters") String empID,
                        @Min(value = 0, message = "must be at least 0000")
                        @Max(value = 2359, message = "must be 2359 or less") int startTime,
                        @Min(value = 0, message = "must be at least 0000")
                        @Max(value = 2359, message = "must be 2359 or less") int endTime,
                        String workDate)
    {
        this.id = id;
        this.empID = empID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.workDate = workDate;
    }

    //Getter for ID
    public Long getId() { return id;}

    //Setter for ID
    public void setId(Long shift_No) { this.id = shift_No;}

    //Getter for empID
    public String getEmpID() { return empID; }

    //Setter for empID
    public void setEmpID(String empID) { this.empID = empID;}

    //Getter for startTime
    public int getStartTime() { return startTime; }

    //Setter for startTime
    public void setStartTime(int startTime) { this.startTime = startTime; }

    //Getter for endTime
    public int getEndTime() { return endTime; }

    //Setter for endTime
    public void setEndTime(int endTime) { this.endTime = endTime;}

    //Getter for workDate
    public String getWorkDate() {return workDate;}

    //Setter for workDate
    public void setWorkDate(String workDate) { this.workDate = workDate;}

    //Getter for created_At
    public Date getCreated_At() { return created_At;}

    //Setter for created_At
    public void setCreated_At(Date created_At) {this.created_At = created_At; }

    //Getter for updated_At
    public Date getUpdated_At() {return updated_At; }

    //Setter for updated_At
    public void setUpdated_At(Date updated_At) {this.updated_At = updated_At; }

    //Sets available to false
    public void makeNotAvailable(){available = false;}

    //Sets available
    public void makeAvailable(){available = true;}

    //Gets availability
    public boolean getAvailable(){return available;}

    //Creates and sets the current date when Working Hours object was created
    @PrePersist
    protected void onCreate() { this.created_At = new Date(); }

    //Creates and sets the current date when Working Hours object was last updated
    @PreUpdate
    protected void onUpdate() { this.updated_At = new Date(); }
}