package io.student.service;

import io.student.Repository.UserAccountRepository;
import io.student.dto.UserAccountDTO;
import io.student.dto.UserAccountInputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserAccountService {

    @Autowired
    UserAccountRepository userAccountRepository;

    public String upsertUserAccount(UserAccountInputDTO inputDTO){
        try{

            if (inputDTO.getAccountNumber()==null || inputDTO.getUserId()==null || inputDTO.getIfsc()==null){
                return "Please enter all the details";
            }
            if (userAccountRepository.checkForAttribute(inputDTO.getAccountNumber(),inputDTO.getUserId())==1){
                userAccountRepository.updateUserAccount(inputDTO.getAccountNumber(), inputDTO.getUserId(), inputDTO.getIfsc());
                return "Account Updated Successfully";
            }else {
                userAccountRepository.addUserAccount(inputDTO.getAccountNumber(), inputDTO.getUserId(), inputDTO.getIfsc());
                return "Account Added Successfully";
            }

        }catch(Exception ex){
            System.out.println("Exception occured ||" + ex.getMessage());
            return "Exception occured ||" + ex.getMessage();
        }
    }

    public Set<UserAccountDTO> getUserAccountByUserId(String userId){
        try{
            return userAccountRepository.getUserAccountByUserId(userId);
        }catch(Exception ex){
            System.out.println("Exception occured ||" + ex.getMessage());
            return null;
        }
    }
}
