package io.student.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountInputDTO {

    private String accountNumber;
    private String userId;

    private String ifsc;
}
