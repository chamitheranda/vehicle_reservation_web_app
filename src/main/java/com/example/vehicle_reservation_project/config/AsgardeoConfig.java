package com.example.vehicle_reservation_project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class AsgardeoConfig {

    @Value("${asgardeo.endpoint}")
    private String asgardeoEndpoint;

    @Value("${asgardeo.clientId}")
    private String clientId;


    public String getAsgardeoEndpoint() {
        return asgardeoEndpoint;
    }

    public String getClientId() {
        return clientId;
    }


}
