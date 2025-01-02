package com.viu.patronAPP.unit.jwt;

import com.viu.patronAPP.config.token.JwtUserDetailsService;
import com.viu.patronAPP.domain.model.Rol;
import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.infrastructure.out.persistence.repository.UserRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JwtDetailsTest {

    @Mock
    private UserRepositoryAdapter userRepositoryAdapter;

    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;

    private static final String EMAIL = "testuser@example.com";
    private static final String PASSWORD = "password123";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_UserFoundWithAdminRole() {
        User user = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .rol(Rol.ADMIN)
                .build();

        when(userRepositoryAdapter.getUserByEmail(EMAIL)).thenReturn(user);

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(EMAIL);

        assertNotNull(userDetails);
        assertEquals(EMAIL, userDetails.getUsername());
        assertEquals(PASSWORD, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    void loadUserByUsername_UserNotFound() {
        when(userRepositoryAdapter.getUserByEmail(EMAIL)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            jwtUserDetailsService.loadUserByUsername(EMAIL);
        });
    }

    @Test
    void loadUserByUsername_UserFoundWithNonAdminRole() {
        User user = User.builder()
                .email(EMAIL)
                .password(PASSWORD)
                .rol(Rol.USER)
                .build();

        when(userRepositoryAdapter.getUserByEmail(EMAIL)).thenReturn(user);

        assertThrows(UsernameNotFoundException.class, () -> {
            jwtUserDetailsService.loadUserByUsername(EMAIL);
        });
    }
}
