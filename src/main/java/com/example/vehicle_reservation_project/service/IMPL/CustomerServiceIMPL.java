package com.example.vehicle_reservation_project.service.IMPL;

import com.example.vehicle_reservation_project.DTO.RequestDTO.DeleteRequestDTO;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ReservationRequestDTO;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ViewAllReservationDTO;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ViewReservationResponseDTO;
import com.example.vehicle_reservation_project.entity.CustomerDetails;
import com.example.vehicle_reservation_project.repo.CustomerRepo;
import com.example.vehicle_reservation_project.service.CustomerService;
import com.example.vehicle_reservation_project.util.CompareDate;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    public String deleteFutureReservation(DeleteRequestDTO deleteRequestDTO) {
        if(customerRepo.existsByEmailAndVehicleNo(deleteRequestDTO.getEmail() , deleteRequestDTO.getVehicleNo())){
            CustomerDetails record = customerRepo.getByEmailAndVehicleNo(deleteRequestDTO.getEmail() , deleteRequestDTO.getVehicleNo());
            if(compareDate.isFuture(record.getDate())){
                customerRepo.delete(record);
                return "Delete reservation successfully";
            }
            return "Service is already done !!" ;
        }

        return "No vehicle reservation for given email : " + deleteRequestDTO.getEmail() + " and vehicle number : " + deleteRequestDTO.getVehicleNo();
    }

    @Override
    public List<ViewReservationResponseDTO> getAllReservationRecords(ViewAllReservationDTO viewAllReservationDTO) throws NotFoundException {
        List<ViewReservationResponseDTO> DTOList = new ArrayList<>();
        if(customerRepo.existsByEmail(viewAllReservationDTO.getEmail())){
            List<CustomerDetails> entityList = customerRepo.getByEmail(viewAllReservationDTO.getEmail());
            for (CustomerDetails c:entityList) {
                ViewReservationResponseDTO x = new ViewReservationResponseDTO(
                        c.getName(),
                        c.getEmail(),
                        c.getDate(),
                        c.getTime(),
                        c.getVehicleNo(),
                        c.getMileage()
                );
                DTOList.add(x);
            }
            return DTOList ;
        }
        throw new NotFoundException("No reservations");
    }
}
