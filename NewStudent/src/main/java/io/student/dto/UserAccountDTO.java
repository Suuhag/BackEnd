package io.student.dto;

import io.student.models.UserDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDTO {

    private String account_number;


    private String user_details_id;


    private String ifsc;
}
