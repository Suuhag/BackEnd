package io.student.controller;

import io.student.dto.UserAccountDTO;
import io.student.dto.UserAccountInputDTO;
import io.student.dto.UserDTO;
import io.student.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserAccountController {

    @Autowired
    UserAccountService userAccountService;

    @PostMapping
    @RequestMapping(value = "/upsertUserAccount")
    public String upsertUserAccount(@RequestBody UserAccountInputDTO inputDTO){
        System.out.println("======>Welcome to the Controller<======");
        return userAccountService.upsertUserAccount(inputDTO);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getUserAccountByUserId")
    public Set<UserAccountDTO> getUserAccountByUserId(@RequestParam String userId){
        System.out.println("reached controller 1");
        return userAccountService.getUserAccountByUserId(userId);
    }
}
