package com.mindex.challenge.data;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Compensation {
	@Id
	private String id;
	private double salary; //double in case someone gets paid in pennies
	private Date effectiveDate;
	private String employeeId;
	
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
