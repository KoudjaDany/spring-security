package com.ddf.training.springsecurity.controllers;

import com.ddf.training.springsecurity.domain.Student;
import com.ddf.training.springsecurity.services.StudentService;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
    public Student getStudent(@PathVariable("studentId") Long id){
                                                                                                                                                                                    return studentService.getDetails(id);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')")
    public List<Student> listAll(){
        return studentService.listAll();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('student:write')")
    public Student createStudent(@RequestBody Student student){
        return studentService.create(student);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('student:write')")
    public Student updateStudent(@RequestBody Student student){
        return studentService.update(student);
    }

    @DeleteMapping("/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.delete(id);
    }

}
