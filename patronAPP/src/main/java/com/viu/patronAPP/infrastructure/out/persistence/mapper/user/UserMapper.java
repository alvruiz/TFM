package com.viu.patronAPP.infrastructure.out.persistence.mapper.user;


import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserEntity mapUserDomainToEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .age(user.getAge())
                .gender(user.getGender())
                .rol(user.getRol())
                .eventsParticipating(user.getEventsParticipating())
                .imageUrl(user.getImageUrl())
                .build();
    }

    public static User mapUserEntityToDomain(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .age(userEntity.getAge())
                .gender(userEntity.getGender())
                .rol(userEntity.getRol())
                .eventsParticipating(userEntity.getEventsParticipating())
                .imageUrl(userEntity.getImageUrl())
                .build();
    }
}
