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
    private String villageId;
    private String imageUrl;
    private List<String> eventsParticipating;
}
