package com.ddf.training.springsecurity.services;

import com.ddf.training.springsecurity.domain.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class StudentServiceImpl implements StudentService{

    private static List<Student> students = List.of(
            new Student(1L, "Franky Mbieleu"),
            new Student(2L, "Innocent Tialo"),
            new Student(3L, "Steves Kamdem"),
            new Student(4L, "Mireille Rhimo"),
            new Student(5L, "Dany Koudja")
    );

    @Override
    public List<Student> listAll(){
        return students;
    }

    public Student getStudent(Long id){
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("Student "+ id + " does not exist."));
    }

    @Override
    public Student getDetails(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("Student "+ id + " does not exist."));
    }

    @Override
    public Student create(Student entity) {
        Long id = students.stream()
                .map(Student::getId)
                .max(Long::compare)
                .orElse(1L)     ;
        entity.setId(id + 1);
        List<Student> list = new ArrayList<>(students);
        list.add(entity);
        students = list;
        return  entity;
    }

    @Override
    public void delete(Long id) {
        students.remove(getDetails(id));
    }

    @Override
    public Student update(Student entity) {
        Student studentToUpdate = students.stream()
                .filter(student -> student.getId().equals(entity.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("The student of id " + entity.getId() + " does not exist."));
        students.add(students.indexOf(studentToUpdate), entity);
        return entity;
    }
}
