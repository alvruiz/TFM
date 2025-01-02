package com.viu.patronAPP.unit.services;

import com.viu.patronAPP.application.services.UserServiceImpl;
import com.viu.patronAPP.domain.model.Rol;
import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.domain.model.exceptions.GeneralException;
import com.viu.patronAPP.domain.model.exceptions.NotFoundException;
import com.viu.patronAPP.domain.ports.out.UserPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @Mock
    private UserPort userPort;
    @InjectMocks
    private UserServiceImpl userService;

    User user = User.builder()
            .id("1")
            .name("Test")
            .password("Test")
            .age(10)
            .email("Test@email.com")
            .eventsParticipating(new ArrayList<>())
            .gender("M")
            .rol(Rol.ADMIN)
            .villageId("1")
            .surname("test")
            .imageUrl("www.test.com")
            .build();

    @Test
    public void testGetUserById() {
        when(userPort.getUserById("1")).thenReturn(user);
        User result = userService.getUserById("1");
        verify(userPort).getUserById("1");
        assert (result != null);
        assert (result.equals(user));
    }

    @Test
    public void testGetUserByIdDoesNotExist() {
        when(userPort.getUserById("1")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.getUserById("1"));
        verify(userPort).getUserById("1");
        assert (exception != null);
        assert (exception.getMessage().equals("User not found"));

    }

    @Test
    public void testCreateExistingUser() {
        when(userPort.getUserByEmail("Test@email.com")).thenReturn(user);
        GeneralException exception = assertThrows(GeneralException.class, () -> userService.createUser(user));
        verify(userPort).getUserByEmail("Test@email.com");
        assert (exception != null);
        assert (exception.getMessage().equals("User already exists"));

    }

    @Test
    public void testCreateUser() {
        when(userPort.getUserByEmail("Test@email.com")).thenReturn(null);

        userService.createUser(user);
        verify(userPort, times(1)).createUser(user);
        verify(userPort, times(1)).getUserByEmail("Test@email.com");
    }

    @Test
    public void testGetUserByEmail() {
        when(userPort.getUserByEmail("Test@email.com")).thenReturn(user);
        User result = userService.getUserByEmail("Test@email.com");
        verify(userPort).getUserByEmail("Test@email.com");
        assert (result != null);
        assert (result.equals(user));
    }

    @Test
    public void testGetUserByEmailDoesNotExist() {
        when(userPort.getUserByEmail("Test@email.com")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.getUserByEmail("Test@email.com"));
        verify(userPort).getUserByEmail("Test@email.com");
        assert (exception != null);
        assert (exception.getMessage().equals("User not found"));
    }

    @Test
    public void testUpdateUser() {
        when(userPort.getUserByEmail("Test@email.com")).thenReturn(user);
        User newUser = User.builder()
                .id("1")
                .name("NewTest")
                .password("Test")
                .age(10)
                .email("Test@email.com")
                .eventsParticipating(new ArrayList<>())
                .gender("M")
                .rol(Rol.ADMIN)
                .villageId("1")
                .surname("test").build();
        userService.updateUser(newUser);
        verify(userPort, times(1)).updateUser(newUser);
        verify(userPort, times(1)).getUserByEmail("Test@email.com");
    }

    @Test
    public void testUpdateUserDoesNotExist() {
        when(userPort.getUserByEmail("Test@email.com")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.updateUser(user));
        verify(userPort, times(1)).getUserByEmail("Test@email.com");
        assert (exception != null);
        assert (exception.getMessage().equals("User not found"));
    }

    @Test
    public void testDeleteUser() {
        when(userPort.getUserById("1")).thenReturn(user);
        userService.deleteUser("1");
        verify(userPort, times(1)).deleteUser("1");
    }

    @Test
    public void testDeleteUserNotFound() {
        when(userPort.getUserById("1")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            userService.deleteUser("Test@email.com");
        });
        assert (exception != null);
        assert (exception.getMessage().equals("User not found"));
    }

    @Test
    public void testLoginPasswordDoesNotMatch() {
        when(userPort.getUserByEmail("Test@email.com")).thenReturn(user);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode("Test");
        user.setPassword(encryptedPassword);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.login("Test@email.com", "Test2"));
        verify(userPort).getUserByEmail("Test@email.com");
        assert (exception != null);
        assert (exception.getMessage().equals("Invalid credentials"));
    }

    @Test
    public void testLogin() {
        when(userPort.getUserByEmail("Test@email.com")).thenReturn(user);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode("Test");
        user.setPassword(encryptedPassword);
        User result = userService.login("Test@email.com", "Test");
        verify(userPort).getUserByEmail("Test@email.com");
        assert (result != null);
        assert (result.equals(user));
    }

    @Test
    public void testLoginDoesNotExist() {
        when(userPort.getUserByEmail("Test@email.com")).thenReturn(null);
        NotFoundException exception = assertThrows(NotFoundException.class, () -> userService.login("Test@email.com", "Test"));
        verify(userPort).getUserByEmail("Test@email.com");
        assert (exception != null);
        assert (exception.getMessage().equals("Invalid credentials"));
    }


}
