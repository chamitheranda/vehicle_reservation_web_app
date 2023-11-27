package com.example.vehicle_reservation_project.service.IMPL;
import com.example.vehicle_reservation_project.DTO.RequestDTO.ReservationRequestDTO;
import com.example.vehicle_reservation_project.DTO.ResponseDTO.ViewReservationResponseDTO;
import com.example.vehicle_reservation_project.entity.CustomerDetails;
import com.example.vehicle_reservation_project.repo.CustomerRepo;
import com.example.vehicle_reservation_project.service.CustomerService;
import com.example.vehicle_reservation_project.util.CompareDate;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private CompareDate compareDate;

    @Override
    public HttpStatus insertReservationData(ReservationRequestDTO reservationRequestDTO) {
        List<CustomerDetails> customers = customerRepo.getByEmail(reservationRequestDTO.getEmail());
        if (customerRepo.existsByEmail(reservationRequestDTO.getEmail())) {
            for (CustomerDetails x : customers) {
                if (compareDate.isFuture(x.getDate()) && x.getVehicleNo().equals(reservationRequestDTO.getVehicleRegistrationNumber())) {
                        return HttpStatus.ALREADY_REPORTED;
                }
            }
        }
        if(customerRepo.existsByEmail(reservationRequestDTO.getEmail())){
            for (CustomerDetails x : customers) {
                if (compareDate.isFuture(x.getDate()) && !x.getVehicleNo().equals(reservationRequestDTO.getVehicleRegistrationNumber())) {
                    CustomerDetails customerDetails = new CustomerDetails(
                            reservationRequestDTO.getUserName(),
                            reservationRequestDTO.getEmail(),
                            reservationRequestDTO.getNumber(),
                            reservationRequestDTO.getReservationDate(),
                            reservationRequestDTO.getPreferredTime(),
                            reservationRequestDTO.getPreferredLocation(),
                            reservationRequestDTO.getVehicleRegistrationNumber(),
                            reservationRequestDTO.getCurrentMileage(),
                            reservationRequestDTO.getMessage()
                    );
                    customerRepo.save(customerDetails);
                    return HttpStatus.OK;
                }
            }
        }
        CustomerDetails customerDetails = new CustomerDetails(
                reservationRequestDTO.getUserName(),
                reservationRequestDTO.getEmail(),
                reservationRequestDTO.getNumber(),
                reservationRequestDTO.getReservationDate(),
                reservationRequestDTO.getPreferredTime(),
                reservationRequestDTO.getPreferredLocation(),
                reservationRequestDTO.getVehicleRegistrationNumber(),
                reservationRequestDTO.getCurrentMileage(),
                reservationRequestDTO.getMessage()
        );
        customerRepo.save(customerDetails);
        return HttpStatus.OK ;
    }

    @Override
    public String deleteFutureReservation(String vNumber) {
        if(customerRepo.existsByVehicleNo(vNumber)){
            CustomerDetails record = customerRepo.getByVehicleNo(vNumber);
            if(compareDate.isFuture(record.getDate())){
                customerRepo.delete(record);
                return "Delete reservation successfully";
            }
            return "Service is already done !!" ;
        }

        return "No vehicle reservation for vehicle number : " + vNumber;
    }

    @Override
    public List<ViewReservationResponseDTO> getAllReservationRecords(String email) throws NotFoundException {
        List<ViewReservationResponseDTO> DTOList = new ArrayList<>();
        if(customerRepo.existsByEmail(email)){
            List<CustomerDetails> entityList = customerRepo.getByEmail(email);
            for (CustomerDetails c:entityList) {
                ViewReservationResponseDTO x = new ViewReservationResponseDTO(
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

    @Override
    public List<ViewReservationResponseDTO> getFutureReservationRecords(String email) throws NotFoundException {
        List<ViewReservationResponseDTO> DTOList = new ArrayList<>();
        if(customerRepo.existsByEmail(email)){
            List<CustomerDetails> entityList = customerRepo.getByEmail(email);
            for (CustomerDetails c:entityList) {
                if(compareDate.isFuture(c.getDate())){
                    ViewReservationResponseDTO x = new ViewReservationResponseDTO(
                            c.getDate(),
                            c.getTime(),
                            c.getVehicleNo(),
                            c.getMileage()
                    );
                    DTOList.add(x);
                }
            }
            return DTOList ;
        }
        throw new NotFoundException("No reservations");
    }
}
