package com.example.springbootcrudoperation.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DuplicateDataFoundException extends RuntimeException{

	public DuplicateDataFoundException(String str) {
		super(str);
	}
}
