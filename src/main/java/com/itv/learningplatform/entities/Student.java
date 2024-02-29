package com.itv.learningplatform.entities;


import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull(message = "name cannot be null")
    private String name;

    @NotEmpty(message = "email cannot be empty")
    private String email;
    
    @DecimalMin(value = "1000000000", message = "Phone number should be at least 10 digits")
    @DecimalMax(value = "9999999999", message = "Phone number should be at most 10 digits")
    private Long mobile;

    
    @ManyToMany(mappedBy = "students")
    private List<Course> courses;

    @OneToOne(mappedBy="students")
    private Enrollment enrollment;

     @CreatedDate
   private Instant createdDate;
   @LastModifiedDate
    private Date  modifiedAt;
    
}
