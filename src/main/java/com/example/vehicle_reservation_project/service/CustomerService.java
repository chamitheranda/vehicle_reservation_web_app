package com.example.vehicle_reservation_project.service;

import com.example.vehicle_reservation_project.DTO.RequestDTO.DeleteRequestDTO;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ReservationRequestDTO;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ViewAllReservationDTO;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ViewReservationResponseDTO;
import javassist.NotFoundException;

import java.util.List;

public interface CustomerService {

    String insertReservationData(ReservationRequestDTO reservationRequestDTO);

    String deleteFutureReservation(DeleteRequestDTO deleteRequestDTO);

    List<ViewReservationResponseDTO> getAllReservationRecords(ViewAllReservationDTO viewAllReservationDTO) throws NotFoundException;
}
