package com.helmes.spring.service;

import com.helmes.spring.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}