package com.example.springbootcrudoperation.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.springbootcrudoperation.Exception.DuplicateDataFoundException;
import com.example.springbootcrudoperation.model.Employee;
import com.example.springbootcrudoperation.model.Experience;
import com.example.springbootcrudoperation.repository.EmployeeRepository;
import com.example.springbootcrudoperation.repository.ExperienceRepository;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {
@Autowired
	private EmployeeRepository emprepo;
@Autowired
   private ExperienceRepository exprepo;

public Employee  create(Employee emp) {
	
	Optional<Employee> email = emprepo.findByEmail(emp.getEmail());
	if(email.isPresent()) 
		throw new DuplicateDataFoundException("Email already exists");
	
	Optional<Employee> mobileno = emprepo.findByMobilenumber(emp.getMobilenumber());

	if(mobileno.isPresent()) 
		throw new DuplicateDataFoundException("moblie number already exists");
	
	 Employee savedEmployee = emprepo.save(emp);

     List<Experience> experiences = emp.getExperience();
     for (Experience exp : experiences) {
         exp.setEmployee(savedEmployee);
     }
     
     savedEmployee.setExperience(experiences);
     return emprepo.save(savedEmployee);
 }

@Transactional
public Employee  update(Employee employee,Long id) {
	
	Optional<Employee> emp= emprepo.findById(id);
	if(emp.isPresent()) {
	Employee existing  = emp.get();
	existing.setName(employee.getName());
	existing.setAddress(employee.getAddress());
	existing.setEmail(employee.getEmail());
	existing.setEmpNo(employee.getEmpNo());
	existing.setMobilenumber(employee.getMobilenumber());
	existing.setStatus(employee.isStatus());

	List<Experience> oldexp = existing.getExperience();
	exprepo.deleteAll(oldexp);
	
     List<Experience> newexperiences = employee.getExperience();
     for (Experience exp : newexperiences) {
          exp.setEmployee(existing);
    }
     existing.setExperience(newexperiences);
     return emprepo.save(existing);
	}
	else
		throw new RuntimeException("employee not found with "+ id);
	
 }




public List<Employee> paginationSort(int pageNo,int pageSize,String sortby) {
		Pageable pageable =  PageRequest.of(pageNo, pageSize, Sort.by(sortby)) ;  
	    Page<Employee> page = emprepo.findAll(pageable); 
	    List<Employee> emp = page.getContent();   
	   return emp;
	 }

public Employee getById(Long id) {
	return emprepo.findById(id).get();
}

public Employee delete(Long id) {
   Employee delete =   emprepo.findById(id).get();
   delete.setStatus(false);
	return emprepo.save(delete);
}


}
