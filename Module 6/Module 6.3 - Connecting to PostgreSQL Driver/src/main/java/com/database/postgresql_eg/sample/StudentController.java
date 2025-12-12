package com.database.postgresql_eg.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return service.getall();
    }
}
