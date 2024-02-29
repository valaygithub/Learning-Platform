package com.itv.learningplatform.services;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import com.itv.learningplatform.entities.Course;
import com.itv.learningplatform.repositories.CourseRepositories;

import com.itv.learningplatform.FileStorageProperties;
@Service
public class CourseService {
    
    @Autowired
    private CourseRepositories courserepositories;
    private final Path rootLocation;
    public CourseService(FileStorageProperties properties)
    {
        this.rootLocation=Paths.get(properties.getUploadDir());
        try{
            Files.createDirectories(rootLocation);
        }
        catch(IOException e)
        {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"upload directory not created");
        }
    }



    public Object uploadFile(Integer id, MultipartFile file) {
     Path destinationFile=this.rootLocation.resolve(Paths.get(file.getOriginalFilename()));
     try{
        InputStream inputStream=file.getInputStream();
        Files.copy(inputStream,destinationFile,StandardCopyOption.REPLACE_EXISTING);
        inputStream.close();
    }
    catch(Exception e)
    {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"file not uploaded");
    }
    String imageUrl=ServletUriComponentsBuilder.fromCurrentContextPath()
                                               .path("/course/download/")
                                               .path(file.getOriginalFilename())
                                               .toUriString();
    Course course=this.courserepositories.findById(id).get();
    course.setImageUrl(imageUrl);
    this.courserepositories.save(course);
    return "file uploaded succesfully...";

    }



    public Resource downloadFile(String filename) {
      Path file=rootLocation.resolve(filename);
      try{
        Resource resource=new UrlResource(file.toUri());
        if(resource.exists()||resource.isReadable())
        {
            return resource;
        }
        else{
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"file not exists");
        }

      }
      catch (Exception e){
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"file not uploaded ");
      }
    }
    
}


