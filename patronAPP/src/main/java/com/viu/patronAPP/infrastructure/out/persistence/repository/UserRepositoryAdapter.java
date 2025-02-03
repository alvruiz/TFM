package com.viu.patronAPP.infrastructure.out.persistence.repository;

import com.viu.patronAPP.domain.model.Rol;
import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.domain.ports.out.UserPort;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.UserEntity;
import com.viu.patronAPP.infrastructure.out.persistence.mapper.UserMapper;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.user.UserMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserPort {

    private final UserMongoRepository userRepository;

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).map(UserMapper::mapUserEntityToDomain).orElse(null);
    }

    @Override
    public void createUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        UserEntity userEntity = UserMapper.mapUserDomainToEntity(user);
        userRepository.save(userEntity);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper::mapUserEntityToDomain).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String currentPassword = user.getPassword();
        if (!currentPassword.startsWith("$2a$") && !currentPassword.startsWith("$2b$") && !currentPassword.startsWith("$2y$")) {
            String encryptedPassword = passwordEncoder.encode(currentPassword);
            user.setPassword(encryptedPassword);
        }

        UserEntity userEntity = UserMapper.mapUserDomainToEntity(user);
        userRepository.save(userEntity);

        return user;
    }


    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<User> getAdmins() {
        return userRepository.findByRol(Rol.ADMIN).stream().map(UserMapper::mapUserEntityToDomain).collect(Collectors.toList());
    }

    public List<User> getCMs() {
        return userRepository.findByRol(Rol.CM).stream().map(UserMapper::mapUserEntityToDomain).collect(Collectors.toList());
    }

    public List<User> getNormalUsers() {
        return userRepository.findByRol(Rol.USER).stream().map(UserMapper::mapUserEntityToDomain).collect(Collectors.toList());
    }

}
