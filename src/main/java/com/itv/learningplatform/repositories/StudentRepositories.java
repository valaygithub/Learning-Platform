package com.itv.learningplatform.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itv.learningplatform.entities.Student;

public interface StudentRepositories extends JpaRepository<Student, Integer>{
    
    List<Student>findByname(String name);
  
    List<Student>findBymobile(Long mobile);
}
