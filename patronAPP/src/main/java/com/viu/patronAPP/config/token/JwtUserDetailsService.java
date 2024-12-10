package com.viu.patronAPP.config.token;

import com.viu.patronAPP.domain.model.Rol;
import com.viu.patronAPP.domain.model.User;
import com.viu.patronAPP.infrastructure.out.persistence.repository.UserRepositoryAdapter;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepositoryAdapter userRepositoryAdapter;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepositoryAdapter.getUserByEmail(email);
        if (user != null) {
            if (user.getRol() != Rol.ADMIN) throw new UsernameNotFoundException("Does not have Rol admin");
            List<GrantedAuthority> authorities = Arrays.asList(user.getRol().name())
                    .stream()
                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                    .collect(Collectors.toList());

            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(), user.getPassword(), authorities);
        } else {
            throw new UsernameNotFoundException(email);
        }
    }
}
