package com.example.springbootcrudoperation.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{

	public EmployeeNotFoundException(String str) {
		super(str);
	}
}
