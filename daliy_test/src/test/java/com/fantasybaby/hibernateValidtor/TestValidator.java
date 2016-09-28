package com.fantasybaby.hibernateValidtor;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fantasybaby.enumP.HelloEnum;
import com.fantasybaby.hibernateValidtor.bean.UserLogin;

public class TestValidator{
	private static Logger _logger = LoggerFactory.getLogger(TestValidator.class);
	@Test
	public void test1(){
		UserLogin user = new UserLogin("", "password", 3);
		//create factory
		ValidatorFactory facotry = Validation.buildDefaultValidatorFactory();
		//get validator
		Validator validator = facotry.getValidator();
		//validtor
		Set<ConstraintViolation<UserLogin>> validate = validator.validate(user);
		_logger.info("what's the fuck");
//		System.out.println(validate);
		validate = validator.validateProperty(user, "remember");
//		System.out.println(validate);
		validator.validateValue(UserLogin.class, "remember", 3);
//		System.out.println(validate);
	}
	//通过参数拿到name
	@Test
	public void test2(){
		HelloEnum[] values = HelloEnum.values();
		for (HelloEnum helloEnum : values) {
			if (helloEnum.getValue().equals("你好")) {
				System.out.println(helloEnum.name());
			}
		}
	}
}
