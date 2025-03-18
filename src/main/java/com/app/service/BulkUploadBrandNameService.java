package com.app.service;

import com.app.entity.Cars.Brand;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class BulkUploadBrandNameService {
    public List<Brand> readExcel(String filePath)throws IOException {
        List<Brand> entities = new ArrayList<Brand>();


        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fis);

        Sheet sheet = workbook.getSheetAt(0);
        Iterator <Row> row = sheet.iterator();

        if(row.hasNext()){
            row.next();
        }
        while (row.hasNext()){
            Row row1 = row.next();
            Brand entity = new Brand();

            entity.setId((long)row1.getCell(0).getNumericCellValue());
            entity.setName(row1.getCell(1).getStringCellValue());

            entities.add(entity);
        }
        workbook.close();
        fis.close();
        return entities;
    }
}

