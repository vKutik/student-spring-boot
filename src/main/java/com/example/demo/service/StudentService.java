package com.example.demo.service;

import com.example.demo.model.Student;

import java.util.List;

public interface StudentService {
    Student insert(Student student);
    void delete(Student student);

    Student findByEmail(String email);

    List<Student> findAll();

}
