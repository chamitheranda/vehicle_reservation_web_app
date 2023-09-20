package com.example.vehicle_reservation_project.repo;
import com.example.vehicle_reservation_project.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<CustomerDetails,Integer> {
    boolean existsByEmail (String email);

    List<CustomerDetails> getByEmail(String email);

    CustomerDetails getByEmailAndVehicleNo(String email, String vehicleNo);

    boolean existsByEmailAndVehicleNo(String email, String vehicleNo);
}
