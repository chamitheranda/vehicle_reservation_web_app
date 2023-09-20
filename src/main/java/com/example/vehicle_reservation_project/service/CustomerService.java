package com.example.vehicle_reservation_project.service;

import com.example.vehicle_reservation_project.DTO.RequestDTO.ReservationRequestDTO;

public interface CustomerService {

    String insertReservationData(ReservationRequestDTO reservationRequestDTO);
}
