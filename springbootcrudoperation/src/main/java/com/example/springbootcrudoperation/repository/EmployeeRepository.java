package com.example.springbootcrudoperation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootcrudoperation.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	Optional<Employee> findByEmail(String email);
    Optional<Employee> findByMobilenumber(String mobilenumber);
}
