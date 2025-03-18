package com.app.controller;

import com.app.entity.Cars.Car;
import com.app.entity.Cars.CarImage;
import com.app.payload.ImageDto;
import com.app.repository.CarImageRepository;
import com.app.repository.CarRepository;
import com.app.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private BucketService bucketService;
    private CarRepository carRepository;
    private CarImageRepository carImageRepository;

    public ImageController(BucketService bucketService, CarRepository carRepository, CarImageRepository carImageRepository) {
        this.bucketService = bucketService;
        this.carRepository = carRepository;
        this.carImageRepository = carImageRepository;
    }
//    // http://localhost:8080/api/v1/images/upload/file/myBucket/car/1
//    @PostMapping(path="/upload/file/{bucketName}/car/{carId}")
//     public ResponseEntity<ImageDto> uploalCarPhotos(
//            @RequestParam MultipartFile file,
//            @PathVariable String bucketName
//
//            ){
//        String url = bucketService.uploadFile(file, bucketName);
//        ImageDto imageDto = new ImageDto();
//        imageDto.setImageurl(url);
//        return new ResponseEntity<>(imageDto,HttpStatus.OK);
//    }

    //save url in the database this url

    // http://localhost:8080/api/v1/images/upload/file/myBucket/car/1
    @PostMapping(path="/upload/file/{bucketName}/car/{carId}")
    public ResponseEntity<CarImage> uploalCarPhotos(
            @RequestParam MultipartFile file,
            @PathVariable String bucketName,
            @PathVariable Long carId

    ){
        String url = bucketService.uploadFile(file, bucketName);
       // ImageDto imageDto = new ImageDto();
       // imageDto.setImageurl(url);
        //return new ResponseEntity<>(imageDto,HttpStatus.OK);
        Car car = carRepository.findById(carId).get();
        CarImage image = new CarImage();
        image.setUrl(url);
        image.setCar(car);
      // CarImage imagesaved = carImageRepository.save(image);

        return new ResponseEntity<>(image,HttpStatus.OK);
    }
}
