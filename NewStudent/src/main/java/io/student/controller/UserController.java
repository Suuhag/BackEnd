package io.student.controller;

import io.student.models.Users;
import io.student.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllusers")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }
}
