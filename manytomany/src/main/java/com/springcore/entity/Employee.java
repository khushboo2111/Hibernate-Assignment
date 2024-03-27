package com.springcore.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int employeeId;
	String employeeName;

	@ManyToMany(mappedBy = "employees",  cascade = CascadeType.ALL)
	private List<Project> projects;

	public List<Project> getProject() {
		return projects;
	}

	public void setProject(List<Project> projects2) {
		this.projects = projects2;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public Employee() {
		super();

	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}
