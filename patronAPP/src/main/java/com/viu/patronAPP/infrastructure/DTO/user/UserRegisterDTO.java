package com.viu.patronAPP.infrastructure.DTO.user;

import com.viu.patronAPP.domain.model.Rol;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserRegisterDTO {
    private String name;
    private String surname;
    private String email;
    private int age;
    private String gender;
    private Rol rol;
    private List<String> eventsParticipating;
    private String villageId;
    private String imageUrl;
    private String password;
}
