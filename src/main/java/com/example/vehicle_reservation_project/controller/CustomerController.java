package com.example.vehicle_reservation_project.controller;

import com.example.vehicle_reservation_project.DTO.RequestDTO.ReservationRequestDTO;
import com.example.vehicle_reservation_project.repo.CustomerRepo;
import com.example.vehicle_reservation_project.service.CustomerService;
import com.example.vehicle_reservation_project.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin()
@RequestMapping(path = "/api/v1/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService ;

    @PostMapping("/make-reservation")
    public ResponseEntity<StandardResponse> makeReservation(@RequestBody ReservationRequestDTO reservationRequestDTO){
        String text = customerService.insertReservationData(reservationRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse
                        (
                                201,
                                "reservation successfully!",
                                text
                        ), HttpStatus.CREATED);
    }

}
