package com.uams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class UamSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(UamSystemApplication.class, args);
	}

}
