package com.example.vehicle_reservation_project.service;

import com.example.vehicle_reservation_project.DTO.RequestDTO.DeleteRequestDTO;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ReservationRequestDTO;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ViewAllReservationDTO;
import com.example.vehicle_reservation_project.DTO.ResponseDTO.ViewReservationResponseDTO;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface CustomerService {

    HttpStatus insertReservationData(ReservationRequestDTO reservationRequestDTO);

    String deleteFutureReservation(String vNumber);

    List<ViewReservationResponseDTO> getAllReservationRecords(String email) throws NotFoundException;

    List<ViewReservationResponseDTO> getFutureReservationRecords(String email) throws  NotFoundException;
}
