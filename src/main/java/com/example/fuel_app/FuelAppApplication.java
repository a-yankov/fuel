package com.example.fuel_app;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FuelAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FuelAppApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	
	}

	public String ala(){
		return "asdda";
	}
}
