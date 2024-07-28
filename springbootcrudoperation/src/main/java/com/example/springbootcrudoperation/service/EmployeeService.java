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
import com.example.springbootcrudoperation.Exception.EmployeeNotFoundException;
import com.example.springbootcrudoperation.model.Employee;
import com.example.springbootcrudoperation.model.Experience;
import com.example.springbootcrudoperation.repository.EmployeeRepository;
import com.example.springbootcrudoperation.repository.ExperienceRepository;

@Service
public class EmployeeService {
@Autowired
	private EmployeeRepository emprepo;
@Autowired
   private ExperienceRepository exprepo;

public Employee  create(Employee emp) {
	
	if( emprepo.findByEmail(emp.getEmail()).isPresent())
		throw new DuplicateDataFoundException("Email already exists");
	if(emprepo.findByMobilenumber(emp.getMobilenumber()).isPresent()) 
		throw new DuplicateDataFoundException("moblie number already exists");
	
	 Employee savedEmployee = emprepo.save(emp);

     List<Experience> experiences = emp.getExperience();
     for (Experience exp : experiences) {
         exp.setEmployee(savedEmployee);
     }
     
     savedEmployee.setExperience(experiences);
     return emprepo.save(savedEmployee);
 }


public Employee  update(Employee employee,Long id) {
	
	Optional<Employee> emp= emprepo.findById(id);
	
	if(emp.isPresent()) {
	Employee existing  = emp.get();
	if(!existing.getEmail().equals(employee.getEmail()) && emprepo.findByEmail(employee.getEmail()).isPresent())
		throw new DuplicateDataFoundException("Email already exist");
	if(!existing.getMobilenumber().equals(employee.getMobilenumber())&& emprepo.findByMobilenumber(employee.getMobilenumber()).isPresent())
		throw new DuplicateDataFoundException("Mobile number already exist");
	
	existing.setName(employee.getName());
	existing.setAddress(employee.getAddress());
	existing.setEmail(employee.getEmail());
	existing.setEmpNo(employee.getEmpNo());
	existing.setMobilenumber(employee.getMobilenumber());
	existing.setStatus(employee.isStatus());
// already existing experience delete
	exprepo.deleteAll(existing.getExperience());
//  new experience add 	
     List<Experience> newexperiences = employee.getExperience();
     for (Experience exp : newexperiences) {
          exp.setEmployee(existing);
    }
     existing.setExperience(newexperiences);

     return emprepo.save(existing);
	}
	else
		throw new EmployeeNotFoundException("employee not found with "+ id);
	
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
