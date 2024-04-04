package io.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

    private String id;
    private String name;
    private String address;
    private String phone_number;
    private String role_id;


}
