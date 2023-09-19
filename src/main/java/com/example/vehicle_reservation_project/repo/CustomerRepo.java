package com.example.vehicle_reservation_project.repo;
import com.example.vehicle_reservation_project.entity.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepo extends JpaRepository<CustomerDetails,Integer> {
}
