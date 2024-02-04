package com.hcm.mistry.mysql.entity;

import org.joda.time.DateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee implements IEmployee{

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;

    private String employeeName;

    private float employeeSalary;
    
    private DateTime created;
    
	private DateTime updated;

    public Employee() {
    }

    public Employee(Long employeeId, String employeeName, float employeeSalary) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        
        this.created = DateTime.now();
		this.updated = DateTime.now();
    }

    public Employee( String employeeName, float employeeSalary) {
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
		this.created = DateTime.now();
		this.updated = DateTime.now();
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public float getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(float employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

	public DateTime getCreated() {
		return created;
	}

	public void setCreated(DateTime created) {
		this.created = created;
	}

	public DateTime getUpdated() {
		return updated;
	}

	public void setUpdated(DateTime updated) {
		this.updated = updated;
	}

	
    
}