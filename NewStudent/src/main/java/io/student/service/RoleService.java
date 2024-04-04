package io.student.service;

import com.fasterxml.uuid.Generators;
import io.student.Repository.RoleRepository;
import io.student.dto.RoleDTO;
import io.student.dto.UserDTO;
import io.student.models.UserDetails;
import io.student.models.UserRole;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public String upsertRole(RoleDTO role){
        try{
            if (role.getId()==null){
                UserRole userRole = new UserRole();
                String id = Generators.timeBasedGenerator().generate().toString();
                userRole.setId(id);
                userRole.setRole(role.getRole());
                roleRepository.save(userRole);
                return "Role Added";
            }else {
                UserRole userRole = roleRepository.getById(role.getId());
                if (userRole==null){
                    return "Invalid id";
                }else {
                    userRole.setRole(role.getRole());
                    roleRepository.save(userRole);
                    return "Role Updated";
                }
            }
        }catch (Exception ex){
            System.out.println("Exception occured  || "+ex.getMessage());
            return "exception occured || "+ex.getMessage();
        }
    }

    public UserRole getroleById(String id){
        return roleRepository.findById(id).orElse(null);

    }


    public Set<RoleDTO> getAllRoles() {
        try {
            System.out.println("-=-=-=service=-=-=-");
            List<UserRole> response = roleRepository.findAll();
            System.out.println("-=-=-=1=-=-=-");
            if (response == null) {
                System.out.println("-=-=-=2=-=-=-");
                return null;
            }
            System.out.println("-=-=-=3=-=-=-");
//            System.out.println("-=-=-=response=-=-=-" + response);

            Type listType = new TypeToken<Set<RoleDTO>>() {
            }.getType();
            ModelMapper modelMapper = new ModelMapper();
            Set<RoleDTO> responselist = modelMapper.map(response, listType);
            return responselist;
        } catch (Exception ex) {
            System.out.println("Exception occured ||" + ex.getMessage());
            return null;
        }

    }

}
