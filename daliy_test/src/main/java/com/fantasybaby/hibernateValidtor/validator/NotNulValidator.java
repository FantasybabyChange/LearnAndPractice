package com.fantasybaby.hibernateValidtor.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.fantasybaby.hibernateValidtor.annatation.UserNameNotNull;

public class NotNulValidator implements ConstraintValidator<UserNameNotNull, String>{

	public boolean isValid(String value, ConstraintValidatorContext context) {
    if(value == null || value.trim().length() == 0 ){
    	
    }
		return false;
	}

	public void initialize(UserNameNotNull constraintAnnotation) {
//		System.out.println(constraintAnnotation.message());		
	}

}
