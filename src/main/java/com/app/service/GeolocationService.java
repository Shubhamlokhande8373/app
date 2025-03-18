package com.app.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeolocationService {

    private static final String API_KEY = "bbc3cc88a0036635e3b3604eb4321463";
    private static final String URL = "http://api.ipstack.com/";

    public String getLocation(String ipAddress) {
        RestTemplate restTemplate = new RestTemplate();
        String url = URL + ipAddress + "?access_key=" + API_KEY;
        return restTemplate.getForObject(url, String.class);
    }

}
