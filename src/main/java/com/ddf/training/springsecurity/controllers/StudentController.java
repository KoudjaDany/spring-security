package com.ddf.training.springsecurity.controllers;

import com.ddf.training.springsecurity.domain.Student;
import com.ddf.training.springsecurity.services.StudentService;
import com.ddf.training.springsecurity.services.StudentServiceImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId") Long id){
        return studentService.getDetails(id);
    }

    @GetMapping("/all")
    public List<Student> listAll(){
        return studentService.listAll();
    }
}
