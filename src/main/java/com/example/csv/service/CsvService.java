package com.example.csv.service;

import com.example.csv.model.StudentInfo;
import com.example.csv.repo.StudentRepository;
import com.opencsv.CSVReader;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    private final StudentRepository repository;

    public CsvService(StudentRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveCsvData(MultipartFile file) throws Exception {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            reader.skip(1);
            List<StudentInfo> entities = new ArrayList<>();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                StudentInfo entity = new StudentInfo();
                entity.setId(Integer.parseInt(nextLine[0]));
                entity.setName(nextLine[1]);
                entity.setAge(Integer.parseInt(nextLine[2]));
                entities.add(entity);
            }
            repository.saveAll(entities);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error reading the CSV file", e);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new RuntimeException("Invalid number format in CSV file", e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("An unexpected error occurred", e);
        }
    }
}
