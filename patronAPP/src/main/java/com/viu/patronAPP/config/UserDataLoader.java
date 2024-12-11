package com.viu.patronAPP.config;

import com.viu.patronAPP.domain.model.Rol;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.UserEntity;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.user.UserMongoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class UserDataLoader {

    @Bean
    public CommandLineRunner loadData(UserMongoRepository userRepository) {
        return args -> {
            // Limpia la colección existente
            userRepository.deleteAll();

            // Crear usuarios usando Builder
            UserEntity admin = UserEntity.builder()
                    .name("admin")
                    .surname("admin")
                    .email("admin@admin.com")
                    .password("$2a$10$S4ZiU6Tjzax17bYc0ejCF.gtbo2qRp7Pw21eoPcbRWwletc6QbuZC")
                    .age(40)
                    .gender("M")
                    .rol(Rol.ADMIN)
                    .villageId("1")
                    .eventsParticipating(Arrays.asList("Evento1", "Evento2"))
                    .build();

            UserEntity user1 = UserEntity.builder()
                    .name("Carlos")
                    .surname("García")
                    .password("$2a$12$yEqw.B7oQ4tE5X0BL7eV4.IykONqjXJ4SKbA0YO1Lmcg9JeEoQ2K6")
                    .email("carlos.garcia@ejemplo.com")
                    .age(29)
                    .gender("M")
                    .rol(Rol.USER)
                    .eventsParticipating(Arrays.asList("Evento1"))
                    .build();

            UserEntity user2 = UserEntity.builder()
                    .name("María")
                    .surname("López")
                    .password("$2a$12$do4Ri9bBOO7l4VgV62z7ruwXJl97NBjw01glJd0KwJzAyKmvHHajK")
                    .email("maria.lopez@ejemplo.com")
                    .age(32)
                    .gender("F")
                    .rol(Rol.USER)
                    .eventsParticipating(Arrays.asList("Evento2"))
                    .build();

            UserEntity user3 = UserEntity.builder()
                    .name("Lucía")
                    .surname("Martínez")
                    .password("$2a$12$gsXsDs6Rz5mE75FjA4NKMz5yblfqqpp2sF1BHYhFl6gA1yn5shczS")
                    .email("lucia.martinez@ejemplo.com")
                    .age(25)
                    .gender("F")
                    .rol(Rol.USER)
                    .eventsParticipating(Arrays.asList("Evento3"))
                    .build();

            UserEntity user4 = UserEntity.builder()
                    .name("Javier")
                    .surname("Fernández")
                    .password("$2a$12$8gFOy0gy5GxwJqACJz9OguobebtZfi6nb/ugYYCHETv8qaERgEXWm")
                    .email("javier.fernandez@ejemplo.com")
                    .age(35)
                    .gender("M")
                    .rol(Rol.USER)
                    .eventsParticipating(Arrays.asList("Evento1", "Evento3"))
                    .build();

            List<UserEntity> users = Arrays.asList(admin, user1, user2, user3, user4);
            userRepository.saveAll(users);

            System.out.println("Users loaded successfully");
        };
    }
}
