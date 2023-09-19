package com.example.vehicle_reservation_project.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin()
@RequestMapping(path = "/api/v1/customer")
public class CustomerController {

    @GetMapping("/end")
    public String display(){
        String x = "hello";
        return x ;
    }

}
