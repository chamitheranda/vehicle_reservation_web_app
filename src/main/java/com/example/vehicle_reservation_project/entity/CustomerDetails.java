package com.example.vehicle_reservation_project.entity;

import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CustomerDetails")

public class CustomerDetails {
    @Id
    @Column(name = "booking_id", length = 40)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId ;

    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @Column(name = "email", length = 255, nullable = false)
    private String email;

    @Column(name = "phone", unique = false , length = 255)
    private String contactNumber;

    @Column(name = "date" , nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "time" , nullable = false)
    private String time;

    @Column(name = "location"  , length = 255)
    private String location;

    @Column(name = "vehicle_no" , nullable = false , length = 15)
    private String vehicleNo;

    @Column(name = "mileage" , nullable = false , length = 15)
    private int mileage;

    @Column(name = "message" , nullable = false , length = 255)
    private String message;

    public CustomerDetails(String name, String email, String contactNumber, Date date, String  time, String location, String vehicleNo, int mileage, String message) {
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.date = date;
        this.time = time;
        this.location = location;
        this.vehicleNo = vehicleNo;
        this.mileage = mileage;
        this.message = message;
    }
}
