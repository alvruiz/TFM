package com.viu.patronAPP.infrastructure.DTO.user;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLoginDTO {
    private String email;
    private String password;
}
