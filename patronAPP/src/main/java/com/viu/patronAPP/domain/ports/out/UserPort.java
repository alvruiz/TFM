package com.viu.patronAPP.domain.ports.out;

import com.viu.patronAPP.domain.model.User;

public interface UserPort {

    public User getUserById(String id);
    public void createUser(User user);
    public User getUserByEmail(String email);
}
