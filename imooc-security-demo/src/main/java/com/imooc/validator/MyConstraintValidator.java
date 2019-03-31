package com.imooc.validator;

import com.imooc.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

	@Autowired
	private HelloService helloService;

	//初始化方法
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("my validator init");
	}

	//校验逻辑
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		helloService.greeting("tom");
		System.out.println(value);
		if(value.equals("admin")){
			return false;
		}
		return true;
	}

}
