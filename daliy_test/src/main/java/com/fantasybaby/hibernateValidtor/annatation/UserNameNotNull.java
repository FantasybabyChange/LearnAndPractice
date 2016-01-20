package com.fantasybaby.hibernateValidtor.annatation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.fantasybaby.hibernateValidtor.validator.NotNulValidator;

//http://www.360doc.com/content/13/1113/00/3639038_328777049.shtml
@Constraint(validatedBy={NotNulValidator.class})
@Target({ElementType.ANNOTATION_TYPE,ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameNotNull {
	String message() default "";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
}
