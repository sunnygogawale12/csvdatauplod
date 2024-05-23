package com.example.csv.repo;

import com.example.csv.model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentInfo, Long> {
}
