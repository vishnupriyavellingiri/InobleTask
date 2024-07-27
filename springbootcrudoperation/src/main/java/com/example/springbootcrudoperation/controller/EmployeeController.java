package com.example.springbootcrudoperation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootcrudoperation.model.Employee;
import com.example.springbootcrudoperation.service.EmployeeService;

@RestController
@RequestMapping("/Employee")
public class EmployeeController{
@Autowired
	private EmployeeService empservice;

//  http://localhost:8080/Employee/add
@PostMapping("/add")
private ResponseEntity<Employee>  saveEmployee(@RequestBody Employee emp){
	Employee saved = empservice.create(emp);
	return new ResponseEntity<Employee>(saved, HttpStatus.CREATED);
}
//  http://localhost:8080/Employee/update/1
@PutMapping("/update/{id}")
private ResponseEntity<Employee>  updateEmployee(@RequestBody Employee emp,@PathVariable Long id){
	Employee saved = empservice.update(emp, id);
	return new ResponseEntity<Employee>(saved, HttpStatus.CREATED);
}

// http://localhost:8080/Employee/paginationsort?pageNo=0&pageSize=2&sortby=id
@GetMapping("/paginationsort")
public ResponseEntity<List<Employee>> paginationsort(
		@RequestParam(value ="pageNo" ,defaultValue = "0",required = false) int pageNo,
	  @RequestParam(value = "pageSize",defaultValue = "5",required = false)int pageSize,
	  @RequestParam(value = "sortby",defaultValue = "id",required = false)String sortby) {
	List<Employee> emp = empservice.paginationSort(pageNo, pageSize,sortby);
	return ResponseEntity.ok(emp);
}

// http://localhost:8080/Employee/1
@GetMapping("{id}")
public ResponseEntity<Employee> getById(@PathVariable long id ){
	return ResponseEntity.ok(empservice.getById(id));
}
//http://localhost:8080/Employee/delete/1
@DeleteMapping("/delete/{id}")
private ResponseEntity<Employee>  saveEmployee(@PathVariable Long id){
	Employee deleted = empservice.delete(id);
	return new ResponseEntity<Employee>(deleted, HttpStatus.CREATED);
}



// input json data
//{
//    "empNo": 101,
//    "name": "sruthi",
//    "email": "sp@example.com",
//    "address": "123 coimbatore",
//    "mobilenumber": "9876765678",
//    "status": true,
//    "experience": [
//        {
//            "companyName": "zoho",
//            "location": "chennai",
//            "noOfYearExperience": 1
//        },
//        {
//            "companyName": "mynthra",
//            "location": "mumbai",
//            "noOfYearExperience": 2
//        }
//    ]
//}

}
