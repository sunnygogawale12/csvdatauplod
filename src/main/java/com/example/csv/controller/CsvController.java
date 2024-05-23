package com.example.csv.controller;


import com.example.csv.service.CsvService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("upload")
public class CsvController {

    private final CsvService csvService;

    public CsvController(CsvService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("/csv")
    public String uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            csvService.saveCsvData(file);
            return "CSV data saved successfully!";
        } catch (Exception e) {
            return "Error saving CSV data: " + e.getMessage();
        }
    }
}