package com.example.vehicle_reservation_project.controller;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ReservationRequestDTO;
import com.example.vehicle_reservation_project.DTO.ResponseDTO.ViewReservationResponseDTO;
import com.example.vehicle_reservation_project.service.CustomerService;
import com.example.vehicle_reservation_project.util.*;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true" ,  methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
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

    @Autowired
    TokenValidationService tokenValidationService ;



    @PostMapping("/make-reservation")
    public ResponseEntity<StandardResponse> makeReservation(
            @RequestBody ReservationRequestDTO reservationRequestDTO,
            @RequestHeader(value = "Authorization") String authorizationHeader){


        try {
                if (tokenValidationService.validateAccessToken(authorizationHeader)) {
                    HttpStatus msg = customerService.insertReservationData(reservationRequestDTO);
                    if(msg == HttpStatus.OK){
                        return new ResponseEntity<>(
                                new StandardResponse(
                                        201,
                                        "Reservation successfully!",
                                        msg
                                ), HttpStatus.CREATED);
                    }else{
                        return new ResponseEntity<>(
                                new StandardResponse(
                                        208,
                                        "Reservation successfully!",
                                        msg
                                ), HttpStatus.ALREADY_REPORTED);
                    }
                } else {
                    return new ResponseEntity<>(
                            new StandardResponse(
                                    401,
                                    "Unauthorized Access",
                                    ""
                            ), HttpStatus.UNAUTHORIZED);
                }

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new StandardResponse(
                            500,
                            "Internal Server Error",
                            "An unexpected error occurred"
                    ), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @DeleteMapping("delete-future-reservation")
    public ResponseEntity<StandardResponse> deleteReservation(@RequestParam String vehicleNo, @RequestHeader(value = "Authorization") String authorizationHeader){
        if(tokenValidationService.validateAccessToken(authorizationHeader)){
            String text = customerService.deleteFutureReservation(vehicleNo);
            return new ResponseEntity<StandardResponse>(
                    new StandardResponse
                            (
                                    200,
                                    "",
                                    text
                            ), HttpStatus.OK);
        }return new ResponseEntity<StandardResponse>(
                new StandardResponse
                        (
                                401,
                                "Unauthorized Access",
                                ""
                        ), HttpStatus.BAD_REQUEST);


    }

    @GetMapping("get-all-reservation")
    public ResponseEntity<StandardResponse> getAllReservation(@RequestParam String email, @RequestHeader(value = "Authorization") String authorizationHeader ) {
        if(tokenValidationService.validateAccessToken(authorizationHeader)){
            try {
                List<ViewReservationResponseDTO> DTOList = customerService.getAllReservationRecords(email);
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
        }return new ResponseEntity<StandardResponse>(
                new StandardResponse
                        (
                                401,
                                "Unauthorized Access",
                                ""
                        ), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("get-future-reservation")
    public ResponseEntity<StandardResponse> getFutureReservation(@RequestParam String email, @RequestHeader(value = "Authorization") String authorizationHeader ) {
        if(tokenValidationService.validateAccessToken(authorizationHeader)){
            try {
                List<ViewReservationResponseDTO> DTOList = customerService.getFutureReservationRecords(email);
                return new ResponseEntity<>(
                        new StandardResponse(
                                200,
                                "Future Reservations retrieved successfully",
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
        }return new ResponseEntity<StandardResponse>(
                new StandardResponse
                        (
                                401,
                                "Unauthorized Access",
                                ""
                        ), HttpStatus.BAD_REQUEST);
    }


}
