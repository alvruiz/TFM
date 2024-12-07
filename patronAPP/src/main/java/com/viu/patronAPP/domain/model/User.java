package com.viu.patronAPP.domain.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;
    private String gender;
    private Rol rol;

    private List<String> eventsParticipating;
}
