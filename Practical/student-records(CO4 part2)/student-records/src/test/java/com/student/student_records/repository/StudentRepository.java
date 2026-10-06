package com.student.student_records.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.student.student_records.model.Student;

public interface StudentRepository extends MongoRepository<Student, String> {
}