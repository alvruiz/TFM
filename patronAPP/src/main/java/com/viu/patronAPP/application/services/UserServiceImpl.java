package com.viu.patronAPP.application.services;

import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.domain.model.exceptions.GeneralException;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.in.UserUseCasesPort;
import com.viu.patronAPP.infrastructure.out.persistence.repository.UserRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserUseCasesPort {

    private final UserRepositoryAdapter userRepository;

    @Override
    public User getUserById(String id) {
        User user = userRepository.getUserById(id);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return user;
    }

    @Override
    public void createUser(User user) {
        try {
            if (userRepository.getUserByEmail(user.getEmail()) != null) {
                throw new GeneralException("User already exists");
            }
            userRepository.createUser(user);
        } catch (Exception e) {
            throw new GeneralException("Failed to create user due to: " + e.getMessage());
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    @Override
    public User login(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            throw new NotFoundException("Invalid credentials");
        }

        return user;
    }

    @Override
    public User updateUser(User user) {
        userRepository.updateUser(user);
        return user;
    }
}
