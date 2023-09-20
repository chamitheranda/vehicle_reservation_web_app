package com.example.vehicle_reservation_project.util;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class CompareDate {
    public boolean isFuture(Date date){
        Date currentDate = new Date();
        if(date.after(currentDate)){
            return true;
        }else{
            return false ;
        }
    }
}
