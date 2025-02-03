package com.example.momoPlans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MomoPlansApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomoPlansApplication.class, args);
	}

}
