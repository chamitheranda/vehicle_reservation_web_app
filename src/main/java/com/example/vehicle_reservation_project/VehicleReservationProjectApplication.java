package com.example.vehicle_reservation_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class VehicleReservationProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleReservationProjectApplication.class, args);
	}

}
