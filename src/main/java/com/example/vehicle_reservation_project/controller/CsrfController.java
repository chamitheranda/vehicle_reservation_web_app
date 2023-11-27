package com.example.vehicle_reservation_project.controller;
import com.example.vehicle_reservation_project.util.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowCredentials = "true" ,  methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*")
@RequestMapping(path = "/api/v1/csrf")
public class CsrfController {

    @Autowired
    TokenValidationService tokenValidationService ;

    @GetMapping("get-csrf")

    public ResponseEntity<?> getCsrfToken(HttpServletRequest request, @RequestHeader(value = "Authorization") String authorizationHeader) {
        if (tokenValidationService.validateAccessToken(authorizationHeader)) {
            CsrfToken csrfToken = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
            if (csrfToken != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.add("CsrfToken", csrfToken.getToken());
                return new ResponseEntity<>(csrfToken, headers, HttpStatus.OK);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("CsrfToken not available.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid  token.");
        }
    }

}
