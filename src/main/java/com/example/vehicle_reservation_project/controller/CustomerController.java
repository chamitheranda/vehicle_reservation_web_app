package com.example.vehicle_reservation_project.controller;

import com.example.vehicle_reservation_project.DTO.RequestDTO.ReservationRequestDTO;
import com.example.vehicle_reservation_project.repo.CustomerRepo;
import com.example.vehicle_reservation_project.service.CustomerService;
import com.example.vehicle_reservation_project.util.CompareDate;
import com.example.vehicle_reservation_project.util.CurrentDateTime;
import com.example.vehicle_reservation_project.util.StandardResponse;
import com.example.vehicle_reservation_project.util.SundayOrNot;
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

    @Autowired
    CurrentDateTime currentDateTime;

    @Autowired
    SundayOrNot sundayOrNot ;

    @Autowired
    CompareDate compareDate ;

    @PostMapping("/make-reservation")
    public ResponseEntity<StandardResponse> makeReservation(@RequestBody ReservationRequestDTO reservationRequestDTO){
        String time1 = reservationRequestDTO.getTime();
        int time = Integer.parseInt(time1);
        if(!sundayOrNot.isSunday(reservationRequestDTO.getDate()) && compareDate.isFuture(reservationRequestDTO.getDate())){
            if(time == 10 | time == 11 | time == 12){
                String text = customerService.insertReservationData(reservationRequestDTO);
                return new ResponseEntity<StandardResponse>(
                        new StandardResponse
                                (
                                        201,
                                        "reservation successfully!",
                                        text
                                ), HttpStatus.CREATED);
            }
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse
                            (
                                    400,
                                    "reservation failed!",
                                    "time is not 10,11,12"
                            ), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<StandardResponse>(
                new StandardResponse
                        (
                                400,
                                "reservation failed!",
                                "data is sunday or old date"
                        ), HttpStatus.BAD_REQUEST);
        }

}
