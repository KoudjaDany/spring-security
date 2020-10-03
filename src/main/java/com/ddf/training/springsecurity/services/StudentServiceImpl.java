package com.ddf.training.springsecurity.services;

import com.ddf.training.springsecurity.domain.Student;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Getter
public class StudentServiceImpl implements StudentService{

    private static List<Student> STUDENTS = List.of(
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

    @Override
    public Student getDetails(Long id) {
        return STUDENTS.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("Student "+ id + " does not exist."));
    }

    @Override
    public Student create(Student entity) {
        Long id = STUDENTS.stream()
                .map(Student::getId)
                .max(Long::compare)
                .orElse(1L);
        entity.setId(id);
        STUDENTS.add(entity);
        return  entity;
    }

    @Override
    public void delete(Long id) {
        STUDENTS.remove(getDetails(id));
    }

    @Override
    public Student update(Student entity) {
        Student studentToUpdate = STUDENTS.stream()
                .filter(student -> student.getId().equals(entity.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The student of id " + entity.getId() + " does not exist."));
        STUDENTS.add(STUDENTS.indexOf(studentToUpdate), entity);
        return entity;
    }
}
