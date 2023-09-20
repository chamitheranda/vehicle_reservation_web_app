package com.example.vehicle_reservation_project.service.IMPL;

import com.example.vehicle_reservation_project.DTO.RequestDTO.ReservationRequestDTO;
import com.example.vehicle_reservation_project.entity.CustomerDetails;
import com.example.vehicle_reservation_project.repo.CustomerRepo;
import com.example.vehicle_reservation_project.service.CustomerService;
import com.example.vehicle_reservation_project.util.CompareDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CompareDate compareDate;

    @Override
    public String insertReservationData(ReservationRequestDTO reservationRequestDTO) {
        if (customerRepo.existsByEmail(reservationRequestDTO.getEmail())) {
            List<CustomerDetails> customers = customerRepo.getByEmail(reservationRequestDTO.getEmail());
            for (CustomerDetails x : customers) {
                if (compareDate.isFuture(x.getDate()) && x.getVehicleNo().equals(reservationRequestDTO.getVehicleNo())) {
                        return "There is already a future reservation";
                }
            }
        }

        CustomerDetails customerDetails = new CustomerDetails(
                reservationRequestDTO.getName(),
                reservationRequestDTO.getEmail(),
                reservationRequestDTO.getContactNumber(),
                reservationRequestDTO.getDate(),
                reservationRequestDTO.getTime(),
                reservationRequestDTO.getLocation(),
                reservationRequestDTO.getVehicleNo(),
                reservationRequestDTO.getMileage(),
                reservationRequestDTO.getMessage()
        );
        customerRepo.save(customerDetails);
        return "Reservation successful";
    }
}
