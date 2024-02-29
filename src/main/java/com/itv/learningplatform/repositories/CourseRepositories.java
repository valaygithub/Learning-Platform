package com.itv.learningplatform.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itv.learningplatform.entities.Course;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"*"},maxAge = 4800,allowCredentials = "false")
public interface CourseRepositories extends JpaRepository<Course, Integer> {
}
