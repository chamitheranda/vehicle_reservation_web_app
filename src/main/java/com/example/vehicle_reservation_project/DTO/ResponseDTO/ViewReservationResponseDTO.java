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
public class ViewReservationResponseDTO {
    private String name;
    private String email;
    private Date date;
    private String time;
    private String vehicleNo;
    private int mileage;
}
