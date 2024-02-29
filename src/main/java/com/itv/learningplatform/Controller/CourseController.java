package com.itv.learningplatform.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.itv.learningplatform.services.CourseService;

@CrossOrigin(origins = {"*"},maxAge = 4800,allowCredentials = "false")
@RestController
public class CourseController {
    @Autowired
  private CourseService courseService;

  @PutMapping("/courses/{id}/upload")
  public ResponseEntity<?>upload(@PathVariable Integer id,@RequestParam MultipartFile file)
  {
    System.out.println("file uploaded succesfully");
    return ResponseEntity.ok(this.courseService.uploadFile(id,file));  
}
 @GetMapping("/course/download/{filename}")
 public ResponseEntity<?>download(@PathVariable String filename){
  Resource resource=this.courseService.downloadFile(filename);
  return ResponseEntity.ok() 
                       .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + filename + "\"")
                       .body(resource);
 }

}
