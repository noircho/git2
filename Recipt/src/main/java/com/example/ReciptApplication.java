package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//public class ReciptApplication extends SpringBootServletInitializer {
public class ReciptApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReciptApplication.class, args);
	}
	
	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ReciptApplication.class);
	}*/

}
