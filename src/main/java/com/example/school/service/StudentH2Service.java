/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 *
 */

// Write your code here
package com.example.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.school.model.Student;
import com.example.school.model.StudentRowMapper;
import com.example.school.repository.StudentRepository;

@Service
public class StudentH2Service implements StudentRepository {

    @Autowired
    private JdbcTemplate db;

    @Override
    public ArrayList<Student> getStudents() {
        List<Student> studentsList = db.query("select * from STUDENT", new StudentRowMapper());
        ArrayList<Student> students1 = new ArrayList<>(studentsList);
        return students1;
    }

    @Override
    public Student getStudentByStudentId(int studentId) {
        try {
            Student student1 = db.queryForObject("select * from STUDENT where studentId = ?", new StudentRowMapper(), studentId);
            return student1;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Student addStudent(Student student1) {
        db.update("insert into STUDENT(studentName,gender,standard) values (?,?,?)",student1.getStudentName(),student1.getGender(),student1.getStandard());
        Student savedStudent = db.queryForObject(
            "select * from STUDENT where studentName = ? and gender = ? and standard = ?",
            new StudentRowMapper(), student1.getStudentName(), student1.getGender(), student1.getStandard()
        );
        return savedStudent;
    }

    @Override
    public String addStudents(ArrayList<Student> studentList) {

        int count = 0;
        for (Student s1 : studentList) {
            db.update("insert into STUDENT(studentName,gender,standard) values (?,?,?)",s1.getStudentName(),s1.getGender(),s1.getStandard());
            count += 1;
        }
        String s = "";
        s = String.format("Successfully added %d students",count);
        return s;
    }

    @Override
    public Student updateStudent(int studentId, Student student1) {

        if(student1.getStudentName() != null){
            db.update("update STUDENT set studentName = ? where studentId = ?", student1.getStudentName(), studentId);
        }

        if(student1.getGender() != null){
            db.update("update STUDENT set gender = ? where studentId = ?", student1.getGender(), studentId);
        }

        if(student1.getStandard() != 0){
            db.update("update STUDENT set standard = ? where studentId = ?", student1.getStandard(), studentId);
        }

        return getStudentByStudentId(studentId);
    }

    @Override
    public void deleteStudent(int studentId) {
      db.update("delete from STUDENT where studentId = ?",studentId);
    }
}