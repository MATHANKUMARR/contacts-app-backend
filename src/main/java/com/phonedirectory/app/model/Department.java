package com.phonedirectory.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Department {
    @Id   
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    //@Column(name="department_id")
    private String departmentId;

    //@Column(name="department_name")
    private String departmentName;

    private String details;

    private int userId;

    public Department(){

    }

    public Department(int id, String departmentId, String departmentName, String details,int userId) {
        this.id = id;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.details = details;
        this.userId = userId;
    }

    public Department(String departmentId, String departmentName, String details) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
