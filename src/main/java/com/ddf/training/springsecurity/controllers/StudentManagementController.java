package com.ddf.training.springsecurity.controllers;

import com.ddf.training.springsecurity.domain.Student;
import com.ddf.training.springsecurity.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {

    private final StudentService studentService;

    public StudentManagementController(StudentService studentService) {
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

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student){
        return studentService.create(student);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student){
        return studentService.update(student);
    }

    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.delete(id);
    }

}
