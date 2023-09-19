package com.example.vehicle_reservation_project.repo;
import com.example.vehicle_reservation_project.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<CustomerDetails,Integer> {
}
