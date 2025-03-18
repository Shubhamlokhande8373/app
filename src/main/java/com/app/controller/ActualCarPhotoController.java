package com.app.controller;
import com.app.entity.Cars.Car;
import com.app.entity.Cars.CarImage;
import com.app.repository.CarRepository;
import com.app.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/actual-car-photos")
public class ActualCarPhotoController {
    private BucketService bucketService;


    public ActualCarPhotoController(BucketService bucketService) {
        this.bucketService = bucketService;
    }
    // http://localhost:8080/api/v1/actual-car-photos/upload/file/myBucket/car/1
    @PostMapping(path = "/upload/file/{bucketName}/car/{carId}")
    public ResponseEntity<CarImage> uploalCarPhotos(
            @RequestParam List<MultipartFile> files,
            @PathVariable String bucketName,
            @PathVariable Long carId

    ) {
        ArrayList<String> carImages = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = bucketService.uploadFile(file, bucketName);
            carImages.add(url);

        }
        return null;
    }
}
