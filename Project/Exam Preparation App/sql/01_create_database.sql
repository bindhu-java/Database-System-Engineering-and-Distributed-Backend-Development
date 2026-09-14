CREATE DATABASE exam_preparation_app;

USE exam_preparation_app;

CREATE TABLE students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    student_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE
);

CREATE TABLE subjects (
    subject_id INT PRIMARY KEY AUTO_INCREMENT,
    subject_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE questions (
    question_id INT PRIMARY KEY AUTO_INCREMENT,
    subject_id INT NOT NULL,
    question_text VARCHAR(500) NOT NULL,
    difficulty VARCHAR(20) NOT NULL,
    correct_answer VARCHAR(255) NOT NULL,
    FOREIGN KEY (subject_id)
        REFERENCES subjects(subject_id)
);

CREATE TABLE quizzes (
    quiz_id INT PRIMARY KEY AUTO_INCREMENT,
    quiz_name VARCHAR(100) NOT NULL,
    subject_id INT NOT NULL,
    FOREIGN KEY (subject_id)
        REFERENCES subjects(subject_id)
);

CREATE TABLE quiz_attempts (
    attempt_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    quiz_id INT NOT NULL,
    score DECIMAL(5,2) NOT NULL,
    attempt_date DATE NOT NULL,
    FOREIGN KEY (student_id)
        REFERENCES students(student_id),
    FOREIGN KEY (quiz_id)
        REFERENCES quizzes(quiz_id)
);