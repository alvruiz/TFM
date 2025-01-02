package com.viu.patronAPP.unit.repository;

import com.viu.patronAPP.domain.model.Rol;
import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.infrastructure.out.persistence.entity.mongo.UserEntity;
import com.viu.patronAPP.infrastructure.out.persistence.repository.UserRepositoryAdapter;
import com.viu.patronAPP.infrastructure.out.persistence.repository.mongo.user.UserMongoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryAdapterTest {
    @Mock
    private UserMongoRepository userMongoRepository;
    @InjectMocks
    private UserRepositoryAdapter userRepositoryAdapter;

    User user = User.builder().id("1")
            .password("1").build();
    UserEntity userEntity = UserEntity.builder().id("1").password("1").build();

    @Test
    public void testGetUserById() {
        when(userMongoRepository.findById("1")).thenReturn(Optional.ofNullable(userEntity));
        User result = userRepositoryAdapter.getUserById("1");
        assert result != null;
        assert result.getId().equals("1");
        verify(userMongoRepository).findById("1");
    }

    @Test
    public void testCreateUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        when(userMongoRepository.save(any())).thenReturn(userEntity);
        userRepositoryAdapter.createUser(user);
        verify(userMongoRepository, times(1)).save(any());
    }

    @Test
    public void testGetUserByEmail() {
        when(userMongoRepository.findByEmail("1")).thenReturn(Optional.ofNullable(userEntity));
        User result = userRepositoryAdapter.getUserByEmail("1");
        assert result != null;
        assert result.getId().equals("1");
        verify(userMongoRepository).findByEmail("1");
    }

    @Test
    public void testUpdateUser() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        when(userMongoRepository.save(any())).thenReturn(userEntity);
        userRepositoryAdapter.updateUser(user);
        verify(userMongoRepository, times(1)).save(any());

    }

    @Test
    public void testDeleteUser() {
        userRepositoryAdapter.deleteUser("1");
        verify(userMongoRepository, times(1)).deleteById("1");

    }

    @Test
    public void testGetAdmin() {
        when(userMongoRepository.findByRol(Rol.ADMIN)).thenReturn(List.of(userEntity));
        userRepositoryAdapter.getAdmins();
        verify(userMongoRepository, times(1)).findByRol(Rol.ADMIN);

    }

    @Test
    public void testGetCM() {
        when(userMongoRepository.findByRol(Rol.CM)).thenReturn(List.of(userEntity));
        userRepositoryAdapter.getCMs();
        verify(userMongoRepository, times(1)).findByRol(Rol.CM);
    }

    @Test
    public void testGetNormalUsers() {
        when(userMongoRepository.findByRol(Rol.USER)).thenReturn(List.of(userEntity));
        userRepositoryAdapter.getNormalUsers();
        verify(userMongoRepository, times(1)).findByRol(Rol.USER);
    }
}
