package com.itv.learningplatform;


    
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.itv.learningplatform.entities.Student;
import com.itv.learningplatform.repositories.StudentRepositories;

@SpringBootTest
public class StudentTest {
    
    @Autowired
    private StudentRepositories studentrepositories;

    // @Test
    // void testCreate()
    // {
    //     Student b=new Student();
    //     b.setId(100);
    //     b.setName("samay");
    //     b.setEmail("samaykhade@gmail.com");
    //     b.setMobile(8547586952L);
    //     studentrepositories.save(b);
    //     assertNotNull(studentrepositories.findById(52).orElse(b));
    // }
    // @Test
    // void testreadall()
    // {
    //     List<Student>list=studentrepositories.findAll();
    //     boolean condition =list.size()>0;
    //     assertEquals(true, condition);

    // }
    @Test
    void testdelete()
    {
        studentrepositories.deleteById(102);
        assertEquals(false, studentrepositories.existsById(102));
    }
}


