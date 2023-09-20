package com.example.vehicle_reservation_project.controller;

import com.example.vehicle_reservation_project.DTO.RequestDTO.DeleteRequestDTO;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ReservationRequestDTO;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ViewAllReservationDTO;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ViewReservationResponseDTO;
import com.example.vehicle_reservation_project.repo.CustomerRepo;
import com.example.vehicle_reservation_project.service.CustomerService;
import com.example.vehicle_reservation_project.util.CompareDate;
import com.example.vehicle_reservation_project.util.CurrentDateTime;
import com.example.vehicle_reservation_project.util.StandardResponse;
import com.example.vehicle_reservation_project.util.SundayOrNot;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("delete-future-reservation")
    public ResponseEntity<StandardResponse> deleteReservation(@RequestBody DeleteRequestDTO deleteRequestDTO){
        String text = customerService.deleteFutureReservation(deleteRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse
                (
                        200,
                        "",
                        text
                ), HttpStatus.OK);
    }

    @GetMapping("get-all-reservation")
    public ResponseEntity<StandardResponse> getAllReservation(@RequestBody ViewAllReservationDTO viewAllReservationDTO) {
        try {
            List<ViewReservationResponseDTO> DTOList = customerService.getAllReservationRecords(viewAllReservationDTO);
            return new ResponseEntity<>(
                    new StandardResponse(
                            200,
                            "Reservations retrieved successfully",
                            DTOList
                    ),
                    HttpStatus.OK
            );
        } catch (NotFoundException ex) {
            return new ResponseEntity<>(
                    new StandardResponse(
                            404,
                            "No reservations found",
                            null
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }



}
