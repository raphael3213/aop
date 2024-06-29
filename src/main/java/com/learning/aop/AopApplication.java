package com.learning.aop;

import com.learning.aop.service.GreetService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AopApplication.class, args);

		GreetService greetService = context.getBean(GreetService.class);

		greetService.greetPeople();
	}

}
