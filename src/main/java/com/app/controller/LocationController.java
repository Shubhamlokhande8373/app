package com.app.controller;

import com.app.service.GeolocationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")  // Added a base mapping for clarity
public class LocationController {

    private final GeolocationService geolocationService;

    public LocationController(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    @GetMapping("/get-location")
    public String getLocation(HttpServletRequest request) {
        String clientIP = getClientIP(request);
        return geolocationService.getLocation("27.7.95.30");
    }
    private String getClientIP(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String xForwardedFor = request.getHeader("X-Forwarded-For");

        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            remoteAddr = xForwardedFor.split(",")[0];
        }
        return remoteAddr;
    }


}
