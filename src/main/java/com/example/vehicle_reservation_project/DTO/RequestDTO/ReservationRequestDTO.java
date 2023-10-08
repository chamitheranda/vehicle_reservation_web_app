package com.example.vehicle_reservation_project.DTO.RequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationRequestDTO {

    private int currentMileage;
    private String email ;
    private String message;
    private String number;
    private String preferredLocation;
    private String preferredTime;
    private Date reservationDate;
    private String userName;
    private String vehicleRegistrationNumber;


}
