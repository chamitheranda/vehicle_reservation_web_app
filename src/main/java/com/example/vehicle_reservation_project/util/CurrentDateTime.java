package com.example.vehicle_reservation_project.util;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class CurrentDateTime {
    public String timeNDate(){
        LocalDateTime now = LocalDateTime.now();
        String dateTimeString = now.toString();
        return dateTimeString;

    }
}
