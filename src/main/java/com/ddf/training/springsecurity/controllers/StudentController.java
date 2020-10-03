package com.ddf.training.springsecurity.controllers;

import com.ddf.training.springsecurity.domain.Student;
import com.ddf.training.springsecurity.services.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable("studentId") Long id){
        return studentService.getStudent(id);
    }
}