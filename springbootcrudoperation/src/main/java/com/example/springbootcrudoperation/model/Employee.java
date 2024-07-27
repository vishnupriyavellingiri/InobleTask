package com.example.springbootcrudoperation.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
	@Column(name = "employee_number")
private Long empNo;	
	
private String name;
	
private String email;
	
private String address;

private String mobilenumber;

private boolean status;

@OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
@JsonManagedReference
private List<Experience> experience;

public List<Experience> getExperience() {
	return experience;
}

public void setExperience(List<Experience> experience) {
	this.experience = experience;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Long getEmpNo() {
	return empNo;
}

public void setEmpNo(Long empNo) {
	this.empNo = empNo;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getMobilenumber() {
	return mobilenumber;
}

public void setMobilenumber(String mobilenumber) {
	this.mobilenumber = mobilenumber;
}


public boolean isStatus() {
	return status;
}

public void setStatus(boolean status) {
	this.status = status;
}

@Override
public String toString() {
	return "Employee [id=" + id + ", empNo=" + empNo + ", name=" + name + ", email=" + email + ", address=" + address
			+ ", mobilenumber=" + mobilenumber + ", status=" + status + ", experience=" + experience + "]";
}

public Employee(Long id, Long empNo, String name, String email, String address, String mobilenumber, boolean status,
		List<Experience> experience) {
	super();
	this.id = id;
	this.empNo = empNo;
	this.name = name;
	this.email = email;
	this.address = address;
	this.mobilenumber = mobilenumber;
	this.status = status;
	this.experience = experience;
}

public Employee() {
	
}

	
}
