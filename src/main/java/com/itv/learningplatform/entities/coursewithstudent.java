package com.itv.learningplatform.entities;
import java.util.List;

import org.springframework.data.rest.core.config.Projection;

@Projection(name="withstudent", types={Course.class, Student.class})
public interface coursewithstudent {

    String getTitle();
    List<Student> getStudents();
   
}