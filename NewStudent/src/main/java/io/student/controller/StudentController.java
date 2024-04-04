package io.student.controller;

import io.student.dto.StudentDTO;
import io.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;


    @PostMapping(value = "/addStudents")
    public void addStudents(@RequestBody List<StudentDTO> studentDTOList) {
        studentService.addStudents2(studentDTOList);
    }

    @PostMapping(value = "/addStudentList")
    public void addStudentList() {
        studentService.addStudentsList();
    }

    @PostMapping(value = "/updateStudentList")
    public void updateStudentList() {
        studentService.updateStudentList();
    }

    @PostMapping(value = "/updateStudent")
    public void updateStudent() {
        studentService.updateStudents();
    }

    @PostMapping(value = "/updateStudents")
    public void updateStudents() {
        System.out.println("native query");
        studentService.updateStudentUsingNativeQuery();
    }

    @PostMapping(value = "/multiMap")
    public void multiMap() {
        System.out.println("multimap");
        studentService.multiMapTestMethod();
    }
}
