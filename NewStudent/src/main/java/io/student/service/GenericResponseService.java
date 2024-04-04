package io.student.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import io.student.Repository.RoleRepository;
import io.student.Repository.UsersRepository;
import io.student.models.UserRole;
import io.student.models.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GenericResponseService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsersRepository userRepository;

//    @Autowired
//    private ObjectMapper objectMapper;

//    public <T> T demoMethod(String input){
//        try{
//            if (input.equals("user")){
//                return (T) userRepository.getUserById("1");
//            } else if (input.equals("role")) {
//                return  (T) roleRepository.getRoleById("1");
//            }else {
//                return (T) "Bad Request";
//            }
//        }catch (Exception ex){
//            System.out.println(ex.getMessage());
//            return (T) "INTERNAL_SERVER_ERROR";
//        }
//    }

        public <T> T demoMethod(String input){
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try{

            if (input.equals("user")){
                List<Users> users = userRepository.getUserById("1");
                CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Users.class);
                return mapper.convertValue( users, listType);
            } else if (input.equals("role")) {
                List<UserRole> role = roleRepository.getRoleById("1");
                CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, UserRole.class);
                return mapper.convertValue( role, listType);
            }else {
                List<String> str = new ArrayList<>();
                str.add("BAD_REQUEST");
                CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class);
                return mapper.convertValue( str, listType);
            }
        }catch (Exception ex){
            List<String> str = new ArrayList<>();
            str.add("INTERNAL_SERVER_ERROR :" +ex.getMessage());
            CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, String.class);
            return mapper.convertValue( str, listType);
        }
    }


//    public <T> T demoMethod(String input, Class<T> responseType) {
//        try {
//            if (input.equals("user")) {
//                return responseType.cast(userRepository.getUserById("1"));
//            } else if (input.equals("role")) {
//                return responseType.cast(roleRepository.getRoleById("1"));
//            } else {
//                return responseType.cast("Bad Request");
//            }
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//            return responseType.cast("INTERNAL_SERVER_ERROR");
//        }
//    }

}
