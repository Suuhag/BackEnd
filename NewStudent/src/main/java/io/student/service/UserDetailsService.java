package io.student.service;

import com.fasterxml.uuid.Generators;
import io.student.Repository.RoleRepository;
import io.student.Repository.UserRepository;

import io.student.dto.UserDTO;
import io.student.dto.UsersDTO;
import io.student.models.UserDetails;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    JdbcTemplate jdbc;

    public String upsertStudentDetails(UserDTO userDTO) {
        try {
            if (userDTO.getId() == null) {
                System.out.println("======1======" + userDTO.getId());

                UserDetails user = new UserDetails();
                String id = Generators.timeBasedGenerator().generate().toString();

                user.setId(id);
                user.setName(userDTO.getName());
                user.setAddress(userDTO.getAddress());
//                user.setRole(roleRepository.getById(userDTO.getRole()));
                user.setPhone_number(userDTO.getPhone_number());

                userRepository.save(user);
                return "Successful";
            } else {
                UserDetails user = userRepository.getById(userDTO.getId());
                if (user == null) {
                    return "Failed || Invaild id";
                } else {
                    user.setPhone_number(userDTO.getPhone_number());
                    user.setName(userDTO.getName());
                    user.setAddress(userDTO.getAddress());
//                    user.setRole(roleRepository.getById(userDTO.getRole()));
                    userRepository.save(user);

                    System.out.println("-------student update-------" + user);
                    return "Successful";
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception occured ||" + ex.getMessage());
            return null;
        }
    }


    public Set<UserDTO> getAllUsers() {
        try {
            List<UserDetails> response = userRepository.findAll();
            if (response == null) {
                return null;
            }
//            System.out.println("-=-=-=response=-=-=-" + response);

            Type listType = new TypeToken<Set<UserDTO>>() {
            }.getType();
            ModelMapper modelMapper = new ModelMapper();
            Set<UserDTO> responselist = modelMapper.map(response, listType);
            return responselist;
        } catch (Exception ex) {
            System.out.println("Exception occured ||" + ex.getMessage());
            return null;
        }

    }


    public UserDTO getStudentById(String id) {
        try {
            UserDetails response = userRepository.getById(id);
            if (response == null) {
                return null;
            }
//            log.info("-=-=-=response=-=-=-" + response);
            ModelMapper modelMapper = new ModelMapper();
            UserDTO responselist = modelMapper.map(response, UserDTO.class);

            return responselist;
        } catch (Exception ex) {
            System.out.println("Exception occured ||" + ex.getMessage());
            return null;
        }

    }


//    public UserDTO getStudentById(String id) {
//        try {
////            UserDetails response = userRepository.getById(id);
//            List<UserDetails> response = userRepository.getStudentByUserId(id);
//            if (response == null) {
//                return null;
//            }
//            System.out.println("-=-=-=response=-=-=-" + response);
//            ModelMapper modelMapper = new ModelMapper();
//            UserDTO responselist = modelMapper.map(response, UserDTO.class);
//
//            return responselist;
//        } catch (Exception ex) {
//            System.out.println("Exception occured ||" + ex.getMessage());
//            return null;
//        }
//
//    }


    public void deleteStudentById(String id) {

        UserDetails response = userRepository.getById(id);
        System.out.println("-=-=-=response=-=-=-" + response);
        userRepository.deleteById(id);
    }


    public String upsertStudentDetails1(UserDTO userDTO) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            if (userDTO.getId() == null) {

                UserDetails newUser = modelMapper.map(userDTO, UserDetails.class);
                String id = Generators.timeBasedGenerator().generate().toString();
                newUser.setId(id);
//                newUser.setRole(roleRepository.getById(userDTO.getRole()));
                userRepository.save(newUser);
                return "Successful";
            } else {
                UserDetails user = userRepository.getById(userDTO.getId());
                if (user == null) {
                    return "Failed || Invaild id";
                } else {
                    System.out.println("updateshow ");
                    UserDetails updateUser = modelMapper.map(userDTO, UserDetails.class);
//                    updateUser.setRole(roleRepository.getById(userDTO.getRole()));
                    userRepository.save(updateUser);
//                    System.out.println("-------student update-------" + user);
                    return "Successful";
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception occured ||" + ex.getMessage());
            return null;
        }
    }


    public String upsertStudentDetails2(UserDTO userDTO) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            if (userDTO.getId() == null) {
                String id = Generators.timeBasedGenerator().generate().toString();
                userRepository.addUser(id, userDTO.getName(), userDTO.getAddress(), userDTO.getPhone_number(), userDTO.getRole());
                return "Successful";
            } else {
                UserDetails user = userRepository.getById(userDTO.getId());
                if (user == null) {
                    return "Failed || Invaild id";
                } else {
                    System.out.println("update");
                    userRepository.updateUser(userDTO.getId(), userDTO.getName(), userDTO.getAddress(), userDTO.getPhone_number(), userDTO.getRole());
                    return "Successful";
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception occured ||" + ex.getMessage());
            return null;
        }
    }


    public Set<UsersDTO> getUserById(String id) {
        try {
            Set<UsersDTO> response = userRepository.getUserById(id);
            System.out.println("=============================>" + response);
            if (response == null) {
                return null;
            }

            return response;
        } catch (Exception ex) {
            System.out.println("Exception occured ||" + ex.getMessage());
            return null;
        }

    }

    public List<UsersDTO> getUsersById(String id) {
        try {
            List<UserDetails> response = userRepository.getStudentByUserId(id);
            if (response == null) {
                return null;
            }
//            log.info("=====================>" + response);
            Type listType = new TypeToken<List<UsersDTO>>() {
            }.getType();
            ModelMapper modelMapper = new ModelMapper();
            List<UsersDTO> responselist = modelMapper.map(response, listType);

            return responselist;
        } catch (Exception ex) {
            System.out.println("Exception occured ||" + ex.getMessage());
            return null;
        }
    }
}
