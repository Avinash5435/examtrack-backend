package com.examtrack.service;

import com.examtrack.model.Student;
import com.examtrack.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return repo.findById(id);
    }

    public Student addStudent(Student student) {
        student.setStatus("pending");
        return repo.save(student);
    }

    public Student updateScore(Long id, String score) {
        Student s = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        s.setScore(score);
        s.setStatus("scored");
        return repo.save(s);
    }

    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }

    public List<Student> filterByExam(String exam) {
        return repo.findByExam(exam.toUpperCase());
    }

    public List<Student> searchStudents(String query) {
        return repo.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }
}