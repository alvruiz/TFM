package com.viu.patronAPP.domain.ports.in;

import com.viu.patronAPP.domain.model.User;

public interface UserUseCasesPort {

    public User getUserById(String id);

    public void createUser(User user);

    public User getUserByEmail(String email);

    public User login(String email, String password);
}
