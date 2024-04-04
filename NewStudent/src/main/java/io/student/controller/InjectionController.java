package io.student.controller;

import io.student.dto.RoleDTO;
import io.student.dto.UdetailsDTO;
import io.student.service.InjectionService;
import io.student.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InjectionController {

    @Autowired
    InjectionService injectionService;


//    @PostMapping
//    @RequestMapping(value = "/login")
//    public String upsertRole(@RequestParam String name,@RequestParam String Password){
//        System.out.println("reached controller");
//
//        return injectionService.login(name, Password);
//    }

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }


    @PostMapping
    @RequestMapping(value = "/login")
    public ResponseEntity<?>  login(@RequestParam String name, @RequestParam String Password){
        System.out.println("reached controller");

        return injectionService.login(name, Password);
    }

    @PostMapping
    @RequestMapping(value = "/login1")
    public ResponseEntity<?>  login1(@RequestParam String name, @RequestParam String Password){
        System.out.println("reached controller || Login 1");
        System.out.println("==name=="+name);
        System.out.println("==Password=="+Password);
        return injectionService.login1(name, Password);
    }

    @GetMapping
    @RequestMapping(value = "/getUserById1")
    public ResponseEntity<?>  getUserById(@RequestParam String id){
        System.out.println("reached controller");

        return injectionService.getUserById(id);
    }

    @PostMapping
    @RequestMapping(value = "/register")
    public String  register(@RequestParam String name, @RequestParam String Password){
        System.out.println("reached controller");

        return injectionService.register(name, Password);
    }

    @GetMapping
    @RequestMapping(value = "/getAllUsers")
    public List<UdetailsDTO> getAllUsers(){
        System.out.println("reached controller");

        return injectionService.getAllUsers();
    }

    @GetMapping
    @RequestMapping(value = "/getUserDetailsById")
    public List<UdetailsDTO> getUserDetailsById(@RequestParam String id){
        System.out.println("reached controller || sql injection");

        return injectionService.getUserDetailsById(id);
    }
}
