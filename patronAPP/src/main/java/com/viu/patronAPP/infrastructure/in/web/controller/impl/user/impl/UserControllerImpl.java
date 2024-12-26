package com.viu.patronAPP.infrastructure.in.web.controller.impl.user.impl;

import com.viu.patronAPP.domain.model.Rol;
import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.domain.ports.in.UserUseCasesPort;
import com.viu.patronAPP.infrastructure.DTO.user.UpdateUsertDTO;
import com.viu.patronAPP.infrastructure.DTO.user.UserDTO;
import com.viu.patronAPP.infrastructure.DTO.user.UserLoginDTO;
import com.viu.patronAPP.infrastructure.DTO.user.UserRegisterDTO;
import com.viu.patronAPP.infrastructure.in.web.controller.impl.user.UserController;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class UserControllerImpl implements UserController {
    private final MeterRegistry registry;
    private final UserUseCasesPort userService;

    @Override
    public ResponseEntity<UserDTO> getUserByEmail(String email) {
        log.info("Get user by email: {}", email);
        User user = userService.getUserByEmail(email);
        UserDTO userDTO = UserDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .age(user.getAge())
                .gender(user.getGender())
                .rol(user.getRol())
                .imageUrl(user.getImageUrl())
                .eventsParticipating(user.getEventsParticipating())
                .build();
        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<UserDTO> createUser(UserRegisterDTO userDTO) {
        log.info("Create user: {}", userDTO.getEmail());
        User user = User.builder()
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .age(userDTO.getAge())
                .gender(userDTO.getGender())
                .rol(userDTO.getRol() == null ? Rol.USER : userDTO.getRol())
                .imageUrl(userDTO.getImageUrl())
                .eventsParticipating(userDTO.getEventsParticipating())
                .build();
        userService.createUser(user);
        return ResponseEntity.ok(UserDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .age(user.getAge())
                .gender(user.getGender())
                .rol(user.getRol())
                .imageUrl(user.getImageUrl())
                .eventsParticipating(user.getEventsParticipating())
                .build());
    }

    @Override
    public ResponseEntity<UserDTO> login(UserLoginDTO userLoginDTO) {
        registry.counter("patronapp_login_attempts").increment();
        log.info("Login user: {}", userLoginDTO.getEmail());
        User user = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        UserDTO userDTO = UserDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .age(user.getAge())
                .villageId(user.getVillageId())
                .gender(user.getGender())
                .rol(user.getRol())
                .imageUrl(user.getImageUrl())
                .eventsParticipating(user.getEventsParticipating())
                .build();
        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<String> deleteUser(String email) {
        log.info("Delete user: {}", email);
        userService.deleteUser(email);
        return ResponseEntity.ok("Deleted");
    }

    @Override
    public ResponseEntity<UserDTO> updateUser(UpdateUsertDTO userDTO) {
        log.info("Update user: {}", userDTO.getEmail());
        User user = User.builder()
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .age(userDTO.getAge())
                .gender(userDTO.getGender())
                .rol(userDTO.getRol())
                .imageUrl(userDTO.getImageUrl())
                .eventsParticipating(userDTO.getEventsParticipating())
                .build();
        userService.updateUser(user);
        return ResponseEntity.ok(UserDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .age(user.getAge())
                .gender(user.getGender())
                .rol(user.getRol())
                .imageUrl(user.getImageUrl())
                .eventsParticipating(user.getEventsParticipating())
                .build());
    }

}