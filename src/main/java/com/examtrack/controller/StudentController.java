package com.examtrack.controller;

import com.examtrack.model.Student;
import com.examtrack.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable Long id) {
        return service.getStudentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student) {
        return ResponseEntity.ok(service.addStudent(student));
    }

    @PatchMapping("/{id}/score")
    public ResponseEntity<Student> updateScore(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {
        return ResponseEntity.ok(service.updateScore(id, body.get("score")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter")
    public List<Student> filter(@RequestParam String exam) {
        return service.filterByExam(exam);
    }

    @GetMapping("/search")
    public List<Student> search(@RequestParam String q) {
        return service.searchStudents(q);
    }
}