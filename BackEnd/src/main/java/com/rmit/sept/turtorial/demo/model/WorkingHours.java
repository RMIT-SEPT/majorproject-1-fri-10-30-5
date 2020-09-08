package com.rmit.sept.turtorial.demo.model;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
public class WorkingHours
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shiftNo;
    @NotBlank(message = "empID is required")
    @Size(min=1,max =25, message = "Please enter 1 to 25 characters")
    private String empID;
    @Min(value= 0, message="must be at least 0000")
    @Max(value=2359, message="must be 2359 or less")
    private int startTime;
    @Min(value= 0, message="must be at least 0000")
    @Max(value=2359, message="must be 2359 or less")
    private int endTime;
    @JsonFormat(pattern ="yyyy-mm-dd")
    private String workDate;
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date created_At;
    @JsonFormat(pattern ="yyyy-mm-dd")
    private Date updated_At;
    @NotBlank(message = "service is required")
    private String service;

    public WorkingHours(){}

    public Long getShift_No() { return shiftNo;}

    public void setShift_No(Long shift_No) { this.shiftNo = shift_No;}

    public String getEmpID() { return empID; }

    public void setEmpID(String empID) { this.empID = empID;}

    public int getStartTime() { return startTime; }

    public void setStartTime(int startTime) { this.startTime = startTime; }

    public int getEndTime() { return endTime; }

    public void setEndTime(int endTime) { this.endTime = endTime;}

    public String getWorkDate() {return workDate;}

    public void setWorkDate(String workDate) { this.workDate = workDate;}

    public Date getCreated_At() { return created_At;}

    public void setCreated_At(Date created_At) {this.created_At = created_At; }

    public Date getUpdated_At() {return updated_At; }

    public void setUpdated_At(Date updated_At) {this.updated_At = updated_At; }

    public String getService() { return service; }

    public void setService(String service) { this.service = service;}

    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_At = new Date();
    }
}
