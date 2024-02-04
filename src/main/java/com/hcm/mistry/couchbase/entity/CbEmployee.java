package com.hcm.mistry.couchbase.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;

import com.hcm.mistry.mysql.entity.IEmployee;

import jakarta.validation.constraints.NotNull;

@Document
public class CbEmployee implements IEmployee {

	@Id
	private String id;
	@Field
	@NotNull
	private Long employeeId;
	@Field
	@NotNull
	private String employeeName;
	@Field
	@NotNull
	private float employeeSalary;
//	@Field
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
//	private DateTime created;
//	@Field
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
//	private DateTime updated;

	

	public CbEmployee(String id, @NotNull Long employeeId, @NotNull String employeeName,
			@NotNull float employeeSalary) {
		super();
		this.id = id;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeSalary = employeeSalary;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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


	@Override
	public int hashCode() {
		int hash = 1;
		if (id != null) {
			hash = hash * 31 + id.hashCode();
		}
		if (employeeId != null) {
			hash = hash * 31 + employeeId.hashCode();
		}
		if (employeeName != null) {
			hash = hash * 31 + employeeName.hashCode();
		}
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj == null) || (obj.getClass() != this.getClass()))
			return false;
		if (obj == this)
			return true;
		CbEmployee other = (CbEmployee) obj;
		return this.hashCode() == other.hashCode();
	}
}