package io.student.controller;

import io.student.dto.RoleDTO;
import io.student.dto.UserDTO;
import io.student.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class RoleController {

    @Autowired
    RoleService roleService;


    @PostMapping(value = "/upsertRole/{email}")
    public String upsertRole(@RequestBody RoleDTO roleDTO,@PathVariable String email){
        System.out.println("reached controller");
        System.out.println("====REQUEST===="+ roleDTO);
        return roleService.upsertRole(roleDTO);
    }

    @GetMapping(value = "/getAllRoles")
    public Set<RoleDTO> getAllRoles(){
        System.out.println("reached controller 1");
        return roleService.getAllRoles();
    }
}
