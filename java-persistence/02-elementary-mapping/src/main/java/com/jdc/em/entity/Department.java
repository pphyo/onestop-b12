package com.jdc.em.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

	private int id;
	private String name;
	private int employeelimitCount;
	
	private LocationAddress location;
	
	private Employee employee;
	
	public LocationAddress getLocation() {
		return location;
	}
	
	public void setLocation(LocationAddress location) {
		this.location = location;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	@Id
	public int getId() {
		return id;
	}

	@Column(nullable = false, length = 100)
	public String getName() {
		return name;
	}

	@Column(nullable = false, name = "employee_limit_count")
	public int getEmployeelimitCount() {
		return employeelimitCount;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmployeelimitCount(int employeelimitCount) {
		this.employeelimitCount = employeelimitCount;
	}

}
