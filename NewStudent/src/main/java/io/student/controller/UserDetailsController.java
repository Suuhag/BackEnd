package io.student.controller;

import io.student.dto.UserDTO;
import io.student.dto.UsersDTO;
import io.student.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserDetailsController {
    @Autowired
    UserDetailsService userDetailsService;

    @PostMapping
    @RequestMapping(value = "/upsertStudentDetails")
    public String upsertStudentInfo(@RequestBody UserDTO userDTO) {
        System.out.println("reached controller");
        System.out.println("====REQUEST====" + userDTO);
        return userDetailsService.upsertStudentDetails(userDTO);
    }


//    @GetMapping
//    @RequestMapping(method = RequestMethod.GET,value = "/getAllStudentDetails")
//    public Set<StudentsInfoDTO> getAllStudentsDetails(){
//        System.out.println("reached controller");
//        return studentService.getAllStudentsDetails();
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAllusersdetails")
    public Set<UserDTO> getAllStudents() {
        System.out.println("reached controller 1");
        return userDetailsService.getAllUsers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getStudentById")
    public UserDTO getStudentById(@RequestParam String id) {
        System.out.println("reached controller 2");
        return userDetailsService.getStudentById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteStudentById")
    public void deleteStudentById(@RequestParam String id) {
        System.out.println("reached controller 2");
        userDetailsService.deleteStudentById(id);
    }


    @PostMapping
    @RequestMapping(value = "/upsertStudentDetails1")
    public String upsertStudentInfo1(@RequestBody UserDTO userDTO) {
        System.out.println("reached controller");
        System.out.println("====REQUEST====" + userDTO);
        return userDetailsService.upsertStudentDetails1(userDTO);
    }

    @PostMapping
    @RequestMapping(value = "/upsertStudentDetails2")
    public String upsertStudentInfo2(@RequestBody UserDTO userDTO) {
        System.out.println("reached controller");
        System.out.println("====REQUEST====" + userDTO);
        return userDetailsService.upsertStudentDetails2(userDTO);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getUserById")
    public Set<UsersDTO> getUserById(@RequestParam String id) {
        System.out.println("reached controller 2");
        return userDetailsService.getUserById(id);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getUsersById")
    public List<UsersDTO> getUsersById(@RequestParam String id) {
        System.out.println("reached controller 2");
        return userDetailsService.getUsersById(id);
    }

}
