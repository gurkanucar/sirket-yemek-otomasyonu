package com.gucarsoft.bulutmdyemek.controller;

import com.gucarsoft.bulutmdyemek.model.Order;
import com.gucarsoft.bulutmdyemek.repository.FoodRepository;
import com.gucarsoft.bulutmdyemek.repository.OrderRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.PropertyTemplate;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/pdf")
public class ExportController {

    @Autowired
    OrderRepository repo;

        @GetMapping()
        public ResponseEntity<InputStreamResource> generatePDF(Long id) {

            String fileName = "dosya";

            XSSFWorkbook workbook = new XSSFWorkbook();

            XSSFCellStyle style = workbook.createCellStyle();
            XSSFCellStyle cellStyle = workbook.createCellStyle();

            style.setBorderBottom(BorderStyle.MEDIUM);
            style.setBorderTop(BorderStyle.MEDIUM);
            style.setBorderRight(BorderStyle.MEDIUM);
            style.setBorderLeft(BorderStyle.MEDIUM);


            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Sheet sheet = workbook.createSheet(fileName);

            Row headerRow = sheet.createRow(0);

            Cell column00 = headerRow.createCell(0);
            column00.setCellValue("İSİM");

            Cell column01 = headerRow.createCell(1);
            column01.setCellValue("YEMEK SİPARİŞİ:        ");

            int i=1;
            for (Order order:repo.findAll()
                 ) {
                Row row = sheet.createRow(i);
                Cell nameCell = row.createCell(0);
                Cell orderCell = row.createCell(1);
                nameCell.setCellValue(order.getName());
                orderCell.setCellValue(order.getFood());
                i++;
            }

            for (int k = 0; k < 20; k++) {
                sheet.autoSizeColumn(k);
            }

            sheet.setColumnWidth(0, 15 * 256);
            sheet.setColumnWidth(4, 30 * 256);


            try {
                workbook.write(out);
            } catch (IOException e) {
                System.out.println("Ops!");
            }
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=calisan-raporu.xlsx");
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(new InputStreamResource(new ByteArrayInputStream(out.toByteArray())));
        }
    }