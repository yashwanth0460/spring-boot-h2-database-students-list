// Write your code here
package com.example.school.repository;

import com.example.school.model.Student;
import java.util.*;

public interface StudentRepository {
    ArrayList<Student> getStudents();
    Student getStudentByStudentId(int studentId);
    Student addStudent(Student student1);
    String addStudents(ArrayList<Student> studentList);
    Student updateStudent(int studentId, Student student1);
    void deleteStudent(int studentId);
}