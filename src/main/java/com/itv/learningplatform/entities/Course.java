package com.itv.learningplatform.entities;



import java.util.List;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;



import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "title cannot be null")
    @NotEmpty(message = "title cannot be empty")    
    private String title;

    private String description;
    private String imageUrl;

    private Integer price;
    
    @ManyToMany
    @JoinTable(
        name="course_student_name",
        joinColumns=@JoinColumn(name="course_id", referencedColumnName="id"),
        inverseJoinColumns=@JoinColumn(name="student_id", referencedColumnName="id")
    )
    private List<Student> students;
   
   
}