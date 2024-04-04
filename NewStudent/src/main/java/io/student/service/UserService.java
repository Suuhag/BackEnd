package io.student.service;

import io.student.Repository.UsersRepository;
import io.student.models.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
}
