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
                    .name("Administrador")
                    .surname("Pérez")
                    .email("admin@ejemplo.com")
                    .age(40)
                    .gender("M")
                    .rol(Rol.ADMIN)
                    .eventsParticipating(Arrays.asList("Evento1", "Evento2"))
                    .build();

            UserEntity user1 = UserEntity.builder()
                    .name("Carlos")
                    .surname("García")
                    .email("carlos.garcia@ejemplo.com")
                    .age(29)
                    .gender("M")
                    .rol(Rol.USER)
                    .eventsParticipating(Arrays.asList("Evento1"))
                    .build();

            UserEntity user2 = UserEntity.builder()
                    .name("María")
                    .surname("López")
                    .email("maria.lopez@ejemplo.com")
                    .age(32)
                    .gender("F")
                    .rol(Rol.USER)
                    .eventsParticipating(Arrays.asList("Evento2"))
                    .build();

            UserEntity user3 = UserEntity.builder()
                    .name("Lucía")
                    .surname("Martínez")
                    .email("lucia.martinez@ejemplo.com")
                    .age(25)
                    .gender("F")
                    .rol(Rol.USER)
                    .eventsParticipating(Arrays.asList("Evento3"))
                    .build();

            UserEntity user4 = UserEntity.builder()
                    .name("Javier")
                    .surname("Fernández")
                    .email("javier.fernandez@ejemplo.com")
                    .age(35)
                    .gender("M")
                    .rol(Rol.USER)
                    .eventsParticipating(Arrays.asList("Evento1", "Evento3"))
                    .build();

            // Guardar usuarios en la base de datos
            List<UserEntity> users = Arrays.asList(admin, user1, user2, user3, user4);
            userRepository.saveAll(users);

            System.out.println("Users loaded successfully");
        };
    }
}
