package io.student.controller;

import io.student.models.UserRole;
import io.student.models.Users;
import io.student.service.GenericResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenericResponseController {

    @Autowired
    GenericResponseService genericResponseService;

    @GetMapping(value = "/genericResponse")
    public <T> T demoMethod(@RequestParam String input){
        return genericResponseService.demoMethod(input);
    }



//    @GetMapping(value = "/genericResponse")
//    public <T> T demoMethod(@RequestParam String input){
//        if (input.equals("user")) {
//            return genericResponseService.demoMethod(input, Users.class );
//
//        }else if (input.equals("role")){
//            return genericResponseService.demoMethod(input, UserRole.class );
//        }else {
//            return "Invalid input";
//        }
//    }
}
