package com.student.student_records.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.student_records.model.Student;
import com.student.student_records.repository.StudentRepository;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository repository;

    public StudentController(StudentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Student> getStudents() {
        return repository.findAll();
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return repository.save(student);
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable String id) {
        repository.deleteById(id);
        return "Student deleted";
    }
}