package com.app.controller;

import com.app.entity.Cars.Brand;
import com.app.repository.BrandRepository;
import com.app.service.BulkUploadBrandNameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/brand/bulk-upload")
public class BrandBulkUploadController {
    private BulkUploadBrandNameService bulkUploadBrandNameService;
    private BrandRepository brandRepository;

    public BrandBulkUploadController(BulkUploadBrandNameService bulkUploadBrandNameService, BrandRepository brandRepository) {
        this.bulkUploadBrandNameService = bulkUploadBrandNameService;
        this.brandRepository = brandRepository;
    }
   // http://localhost:8080/api/v1/brand/bulk-upload/upload
    @PostMapping("/upload")
    public String uploadExcelFile(){
        try{
            String filePath="C:\\6th August Codes\\Book1.xlsx";
            List<Brand> brands = bulkUploadBrandNameService.readExcel(filePath);
           brandRepository.saveAll(brands);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        return "uploaded";
    }

}