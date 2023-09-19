package com.example.vehicle_reservation_project.controller;

import com.example.vehicle_reservation_project.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping(path = "/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerRepo customerRepo ;

    @GetMapping("/end")
    public String display(){
        String x = "hello";
        return x ;
    }

}
