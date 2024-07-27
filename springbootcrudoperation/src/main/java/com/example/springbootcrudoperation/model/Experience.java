package com.example.springbootcrudoperation.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Experience {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String companyName;
	private String location;
	@Column(name = "no_of_years")
	private int noOfYearExperience;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empId", referencedColumnName = "id")
	@JsonBackReference
    private Employee employee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getNoOfYearExperience() {
		return noOfYearExperience;
	}

	public void setNoOfYearExperience(int noOfYearExperience) {
		this.noOfYearExperience = noOfYearExperience;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Experience [id=" + id + ", companyName=" + companyName + ", location=" + location
				+ ", noOfYearExperience=" + noOfYearExperience + ", employee=" + employee + "]";
	}

	public Experience(Long id, String companyName, String location, int noOfYearExperience, Employee employee) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.location = location;
		this.noOfYearExperience = noOfYearExperience;
		this.employee = employee;
	}
	
	public Experience() {
		
	}
	
}
