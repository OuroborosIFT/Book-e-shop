package com.dsu.arslan.coursework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CourseworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseworkApplication.class, args);
//		ConfigurableApplicationContext context = SpringApplication.run(CourseworkApplication.class, args);
//		PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
//		System.out.println(encoder.encode("admin"));
	}

}
