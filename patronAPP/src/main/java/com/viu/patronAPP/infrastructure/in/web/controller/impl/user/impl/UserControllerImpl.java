package com.viu.patronAPP.infrastructure.in.web.controller.impl.user.impl;

import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.domain.ports.in.UserUseCasesPort;
import com.viu.patronAPP.infrastructure.DTO.user.UserDTO;
import com.viu.patronAPP.infrastructure.DTO.user.UserLoginDTO;
import com.viu.patronAPP.infrastructure.in.web.controller.impl.user.UserController;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserUseCasesPort userService;

    @Override
    public ResponseEntity<UserDTO> getUserByEmail(String email) {
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
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .age(userDTO.getAge())
                .gender(userDTO.getGender())
                .rol(userDTO.getRol())
                .imageUrl(userDTO.getImageUrl())
                .eventsParticipating(userDTO.getEventsParticipating())
                .build();
        userService.createUser(user);
        return ResponseEntity.ok(userDTO);
    }

    @Override
    public ResponseEntity<UserDTO> login(UserLoginDTO userLoginDTO) {
        User user = userService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
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

}