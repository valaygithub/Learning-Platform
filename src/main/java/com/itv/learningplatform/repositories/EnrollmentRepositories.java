package com.itv.learningplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itv.learningplatform.entities.Enrollment;

public interface EnrollmentRepositories  extends JpaRepository<Enrollment, Integer>{
    
}
