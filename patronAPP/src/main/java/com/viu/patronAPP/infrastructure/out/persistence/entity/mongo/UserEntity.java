package com.viu.patronAPP.infrastructure.out.persistence.entity.mongo;

import com.viu.patronAPP.domain.model.Rol;
import lombok.Builder;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
@Builder
public class UserEntity {

    @Id
    private String id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;
    private String gender;
    private Rol rol;
    private String villageId;

    private List<String> eventsParticipating;
}
