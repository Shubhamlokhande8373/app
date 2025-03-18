package com.app.controller;

import com.app.entity.Cars.Car;
import com.app.repository.CarRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/search-car")
public class SearchCarController {

    private CarRepository carRepository;

    public SearchCarController( CarRepository carRepository) {

        this.carRepository = carRepository;
    }

    //http://localhost:8080/api/v1/search-car/cars?brand=honda
    //http://localhost:8080/api/v1/search-car/cars?param=honda
    //http://localhost:8080/api/v1/search-car/cars?param=auto
    @GetMapping ("/cars")
    public List<Car> searchCar(
          @RequestParam String param // brand
    ){

       // Brand carBrandName = brandRepository.findByName(brand);
       return carRepository.searchCar(param);// brand



    }
}
