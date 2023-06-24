/*
 *
 * You can use the following import statemets
 *
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 *
 */
package com.example.school.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.school.model.Student;
import com.example.school.service.StudentH2Service;
import java.util.*;

@RestController
public class StudentController {
    
    @Autowired
    public StudentH2Service studentService;

    @GetMapping("/students")
    public ArrayList<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping("/students/{studentId}")
    public Student getStudentByStudentId(@PathVariable("studentId") int studentId) {
        return studentService.getStudentByStudentId(studentId);
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student1) {
        return studentService.addStudent(student1);

    }

    @PostMapping("/students/bulk")
    public String addStudents(@RequestBody ArrayList<Student> students) {
        return studentService.addStudents(students);

    }
    
    @PutMapping("/students/{studentId}")
    public Student updateStudent(@PathVariable("studentId") int studentId, @RequestBody Student student1) {
        return studentService.updateStudent(studentId, student1);
    }
    
    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable("studentId") int studentId) {
        studentService.deleteStudent(studentId);
    }

}