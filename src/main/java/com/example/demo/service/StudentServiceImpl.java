package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student insert(Student student) {
        return studentRepository.insert(student);
    }

    @Override
    public void delete(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student findByEmail(String email) {
        return studentRepository
                .findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Email address cannot find " + email));
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }
}
