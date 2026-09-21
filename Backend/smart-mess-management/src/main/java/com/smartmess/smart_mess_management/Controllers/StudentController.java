package com.smartmess.smart_mess_management.Controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartmess.smart_mess_management.Services.StudentService;
import com.smartmess.smart_mess_management.entity.Student;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> create(
            @Valid @RequestBody Student student) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.createStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                studentService.getStudentById(id));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {

        return ResponseEntity.ok(
                studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(
            @PathVariable Long id,
            @Valid @RequestBody Student student) {

        return ResponseEntity.ok(
                studentService.updateStudent(id, student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.noContent().build();
    }
}
