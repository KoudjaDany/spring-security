package com.ddf.training.springsecurity.services;

import com.ddf.training.springsecurity.domain.Student;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Getter
public class StudentService {

    private static final List<Student> STUDENTS = List.of(
            new Student(1L, "Franky Mbieleu"),
            new Student(2L, "Innocent Tialo"),
            new Student(3L, "Steves Kamdem"),
            new Student(4L, "Mireille Rhimo"),
            new Student(5L, "Dany Koudja")
    );

    public Student getStudent(Long id){
        return STUDENTS.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("Student "+ id + " does not exist."));
    }
}
