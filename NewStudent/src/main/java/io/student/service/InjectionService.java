package io.student.service;

import com.fasterxml.uuid.Generators;
import io.student.Repository.UsersRepository;
import io.student.dto.UdetailsDTO;
import io.student.models.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLSyntaxErrorException;
import java.util.List;

@Slf4j
@Service
public class InjectionService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    JdbcTemplate jdbc;


//    public ResponseEntity<?> login(String name, String password){
//        try{
//            Integer count = usersRepository.checkForCredentials(name,password);
//            if (count==1){
//                return new ResponseEntity<>("success", HttpStatus.OK);
//            }else {
//                return new ResponseEntity<>("failed", HttpStatus.BAD_REQUEST);
//            }
//
//        }catch (Exception ex){
//            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    public ResponseEntity<?> login(String name, String password) {
        try {
            Integer i = jdbc
                    .queryForObject("select count(*) from users where name=" + name + " and password =" + password, Integer.class);
            log.info("=====i====" + i);
            if (i == 0) {
                return new ResponseEntity<>("Login Failed", HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                return new ResponseEntity<>("Login Successful", HttpStatus.OK);
            }

        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<?> login1(String name, String password) {
        try {
            UdetailsDTO response = jdbc.queryForObject("select * from users where name=? and password =?",
                    new Object[]{name, password}, new BeanPropertyRowMapper<UdetailsDTO>(UdetailsDTO.class));
            if (response == null) {
                return new ResponseEntity<>("Login Failed", HttpStatus.BAD_REQUEST);
            } else {
//                log.info("=====i===="+response);
                return new ResponseEntity<>("Login Successful", HttpStatus.OK);
            }
        } catch (Exception ex) {
//            log.info("=====Exception===="+ex.getMessage());
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getUserById(String id) {
        try {
            List<Users> users = usersRepository.getUserById(id);
//            log.info("========>"+users);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public String register(String name, String password) {
        String id = Generators.timeBasedGenerator().generate().toString();
        jdbc.execute("insert into users(id,name,password) values(" + id + "," + name + "," + password + ")");
        return "Successful";
    }


    public List<UdetailsDTO> getAllUsers() {
        return jdbc.query("SELECT * FROM users", new BeanPropertyRowMapper<UdetailsDTO>(UdetailsDTO.class));
    }


    public List<UdetailsDTO> getUserDetailsById(String id) {
        try {
//            log.info("reached service");
            List<UdetailsDTO> response = jdbc.query("SELECT * FROM users WHERE id =" + id,
                    new BeanPropertyRowMapper<UdetailsDTO>(UdetailsDTO.class));
//            log.info("====Response==>"+response);
            return response;

        } catch (Exception ex) {
//            log.info("Exception Occured  || "+ex.getMessage());
            return null;
        }

    }
}
